package com.test.heroku.common;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.COSObjectable;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationMarkup;
import org.apache.pdfbox.pdmodel.interactive.form.PDCheckbox;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.pdmodel.interactive.form.PDRadioCollection;
import org.apache.pdfbox.util.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class PdfVerifier {

    public PdfVerifier(){
    }

    public PDDocument getPDFDocumentFromFile(String filePath) throws IOException {
        File file = new File(filePath);
        PDDocument document = PDDocument.load(file);
        return document;
    }

    public String getPlainTextFromPdf(String filePath) {
        PDFTextStripper textStripper = null;
        PDDocument document = null;
        String pdfText = null;

        try{
            document = this.getPDFDocumentFromFile(filePath);
            int endPage = document.getNumberOfPages();
            if(endPage == 0){
                System.out.println("WARNING: PDF document is NULL");
            } else {
                textStripper.setStartPage(1);
                textStripper.setEndPage(endPage);

                pdfText = textStripper.getText(document);
            }
        } catch(IOException e){
            throw new RuntimeException(e);
        }

        return pdfText;
    }

    public int getTotalNumberOfPagesInPdf(String absoluteFilePath){
        PDDocument document = null;

        try{
            document = this.getPDFDocumentFromFile(absoluteFilePath);
            int totalPages = document.getNumberOfPages();
            return totalPages;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            //closing the open document
            if(document != null){
                try {
                    document.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public int getFreeTextPageNumber(String filePath, String text) {
        // File --> PDDocument --> PDCatalog --> PDPage --> PDAnnotation --> getContents(text) ---> to work on PDF page numbers
        PDDocument document = null;

        try{
            document = this.getPDFDocumentFromFile(filePath);
            int pageCount = document.getNumberOfPages();

            for(int pageNo = 0; pageNo < pageCount; ++pageNo){
                PDPage page = (PDPage) document.getDocumentCatalog().getAllPages().get(pageNo);
                List<PDAnnotation> annotations = page.getAnnotations();
                Iterator annotationIterator = annotations.iterator();

                while(annotationIterator.hasNext()){
                    PDAnnotation annotation = (PDAnnotation) annotationIterator.next();
                    if(annotation instanceof PDAnnotationMarkup && text.equals(annotation.getContents())){
                        int tempPageNo = pageNo +1;
                        return tempPageNo;
                    }
                }
            }
            byte temp = -1;
            return temp;
        } catch (IOException e){
            throw new RuntimeException(e);
        } finally {
            //closing the open document
            if(document != null){
                try {
                    document.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public int findPageNumberOfPDFField(String fieldName, String filePath) throws IOException {
        PDDocument document = this.getPDFDocumentFromFile(filePath);
        PDDocumentCatalog documentCatalog = document.getDocumentCatalog();

        int pageNumber = 0;
        List<PDPage> pages = documentCatalog.getAllPages();
        List<PDField> fields = documentCatalog.getAcroForm().getFields();
        Iterator fieldIterator = fields.iterator();

        while(true){
            while(fieldIterator.hasNext()){
                PDField field = (PDField) fieldIterator.next();
                if(field instanceof PDRadioCollection){
                    List<COSObjectable> kids = field.getKids();
                    Iterator kidsIterator = kids.iterator();

                    while(kidsIterator.hasNext()){
                        COSObjectable kid= (COSObjectable)kidsIterator.next();
                        PDCheckbox checkbox = (PDCheckbox)kid;
                        if(field.getFullyQualifiedName().equalsIgnoreCase(fieldName)){
                            pageNumber = pages.indexOf(checkbox.getWidget().getPage()) +1;
                        }
                    }
                } else if(field.getFieldType().equals("Tx")) {
                    if(field.getKids() == null){
                        if(field.getFullyQualifiedName().equalsIgnoreCase(fieldName)){
                            pageNumber = pages.indexOf(field.getWidget().getPage()) + 1;
                        }
                    } else {
                        Iterator kidsIterator = field.getKids().iterator();

                        while(kidsIterator.hasNext()){
                            COSObjectable kid = (COSObjectable) kidsIterator.next();
                            PDField pdField = (PDField) kid;
                            if(field.getFullyQualifiedName().equalsIgnoreCase(fieldName)){
                                pageNumber = pages.indexOf(pdField.getWidget().getPage()) +1;
                            }
                        }
                    }
                } else if(field.getFieldType().equals("Btn")){
                    PDCheckbox checkbox = (PDCheckbox) field;
                    if(field.getFullyQualifiedName().equalsIgnoreCase(fieldName)){
                        pageNumber = pages.indexOf(checkbox.getWidget().getPage()) +1;
                    }
                }
            }
            return pageNumber;
        }
    }


}
