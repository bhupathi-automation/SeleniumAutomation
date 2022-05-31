package utils;

import com.test.heroku.common.PdfVerifier;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class PdfVerifierUtil {
    // File --> PDDocument --> PDFTextStripper object

    static PdfVerifier pdfVerifier = new PdfVerifier();

    public static boolean verifyTextPresentInPdf(String filePath, String text){
        String fullPdfText = pdfVerifier.getPlainTextFromPdf(filePath);

        if(fullPdfText.contains(text)){
            System.out.println("PASS: Text is present in PDF");
            return true;
        } else {
            System.out.println("FAIL: Text is NOT present in PDF");
            return false;
        }
    }










//    public static void main(String[] args) {
//        String s = "This is Testing PhaseOfLife";
//        System.out.println(s.contains("est") );
//    }

}
