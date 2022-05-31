package utils;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

public class ExcelReader {

    //    File file = new File(path);
    public String path;
    public FileInputStream fis = null;
    public FileOutputStream fos = null;
    private XSSFWorkbook workbook = null;
    private XSSFSheet sheet = null;
    private Row row = null;
    private Cell cell = null;

    private static String filePath = new String("D:\\workspace\\SeleniumAutomationTesting\\src\\main\\resources\\TestDoc.xlsx");
    private List<List<String>> data = new ArrayList<List<String>>();

    /*********** Constructor ***********/
    public ExcelReader(String path) {
        this.path = path;
        try {
            fis = new FileInputStream(path);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheetAt(0);
            fis.close();

        } catch (FileNotFoundException e) {
            System.out.println("***** WARNING! SPECIFIED FILE IS NOT FOUND IN THE LOCATION PROVIDED *****");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("***** WARNING! WORKBOOK IS NOT ACCESSIBLE *****");
            e.printStackTrace();
        }
    }

    /*********** Returns the row count in a sheet ***********/
    public int getRowCount(String sheetName) {
        int sheetIndex = workbook.getSheetIndex(sheetName);
        if (!isSheetExist(sheetName))
            return 0;
        else {
            sheet = workbook.getSheetAt(sheetIndex);
            int rowCount = sheet.getLastRowNum() + 1 ;
            return rowCount;
        }
    }

    /*********** Returns the column count in a sheet ***********/
    public int getColumnCount(String sheetName) {
        int sheetIndex = workbook.getSheetIndex(sheetName);
        if (!isSheetExist(sheetName))
            return 0;
        else {
            sheet = workbook.getSheetAt(sheetIndex);
            row = sheet.getRow(0);
            int colCount = row.getLastCellNum() ;
            return colCount;
        }
    }

    //TODO
    /*********** Returns the data from a cell - for column name ***********/
    public String getCellData(String sheetName, int rowNum, String colName) {
        try {
            int sheetIndex = workbook.getSheetIndex(sheetName);
            if (sheetIndex == -1)
                return "";
            if (rowNum <= 0)
                return "";

            //find out column number first
            sheet = workbook.getSheetAt(sheetIndex);
            row = sheet.getRow(0);
            int colNumber = -1;
            for (int i = 0; i < row.getLastCellNum(); i++) {
                String colValue = row.getCell(i).getStringCellValue().trim();
                if (colValue.equalsIgnoreCase(colName.trim())) {
                    colNumber = i;
                    break;
                }
            }
            // get cell data based on Cell Type
            row = sheet.getRow(rowNum - 1);
            cell = row.getCell(colNumber);

            if (row == null)
                return "";
            if (cell == null)
                return "";

            if (cell.getCellType() == CellType.STRING) {
                return cell.getStringCellValue();
            } else if (cell.getCellType() == CellType.NUMERIC || cell.getCellType() == CellType.FORMULA) {
                String cellValue = String.valueOf(cell.getNumericCellValue());

                if(HSSFDateUtil.isCellDateFormatted(cell)){
                    double dt = cell.getNumericCellValue();

                    //Format the date in the form of D/M/YYYY
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(HSSFDateUtil.getJavaDate(dt));  /* This sets the calendar instance to given date instance */
//                    System.out.println(cal.YEAR +"\t" + cal.MONTH +"\t" + cal.DAY_OF_MONTH); ----> this will you default values only

                    cellValue = cal.get(Calendar.DAY_OF_MONTH) + "/" + (cal.get(Calendar.MONTH) +1) + "/" + cal.get(Calendar.YEAR);
                    System.out.println("cell Date is: "+cellValue);
                }
                return String.valueOf(cellValue);

            } else if (cell.getCellType() == CellType.BOOLEAN) {
                return String.valueOf(cell.getBooleanCellValue());
            } else {
                return "";
            }
        } catch (Exception e) {
            return "row " + rowNum + " or column " + colName + "does not exist in the excel sheet";
        }

    }

    /*********** Returns the data from a cell - for column number ***********/
    public String getCellData(String sheetName, int rowNum, int colNum) {
        try {
            int sheetIndex = workbook.getSheetIndex(sheetName);
            if (sheetIndex == -1)
                return "";
            if (rowNum <= 0)
                return "";

            // get cell data based on Cell Type
            row = sheet.getRow(rowNum - 1);
            cell = row.getCell(colNum);

            if (row == null)
                return "";
            if (cell == null)
                return "";

            if (cell.getCellType() == CellType.STRING) {
                return cell.getStringCellValue();
            } else if (cell.getCellType() == CellType.NUMERIC || cell.getCellType() == CellType.FORMULA) {
                String cellValue = String.valueOf(cell.getNumericCellValue());

                if(HSSFDateUtil.isCellDateFormatted(cell)){
                    double dt = cell.getNumericCellValue();

                    //Format the date in the form of D/M/YYYY
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(HSSFDateUtil.getJavaDate(dt));  /* This sets the calendar instance to given date instance */
//                    System.out.println(cal.YEAR +"\t" + cal.MONTH +"\t" + cal.DAY_OF_MONTH); ----> this will you default values only

                    cellValue = cal.get(Calendar.DAY_OF_MONTH) + "/" + (cal.get(Calendar.MONTH) +1) + "/" + cal.get(Calendar.YEAR);
                    System.out.println("cell Date is: "+cellValue);
                }
                return String.valueOf(cellValue);

            } else if (cell.getCellType() == CellType.BOOLEAN) {
                return String.valueOf(cell.getBooleanCellValue());
            } else {
                return "";
            }
        } catch (Exception e) {
            return "row " + rowNum + " or column " + colNum + "does not exist in the excel sheet";
        }
    }

    /*********** sets cell data - for column number - returns true if data is set else false ***********/
    public boolean setCellData(String sheetName, int rowNum, String colName, String data) {

        return true;
    }

    /************ Creates sheet - returns true if successful */
    public boolean addSheet(String sheetName) {
        FileOutputStream fileOut;

        try {
            fileOut = new FileOutputStream(path);
            workbook.createSheet(sheetName);
            workbook.write(fileOut);
            fileOut.close();
            if(isSheetExist(sheetName))
                return true;
            else
                return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /************ Removes sheet - returns true if successful */
    public boolean removeSheet(String sheetName) {
        FileOutputStream fileOut;

        try {
            fileOut = new FileOutputStream(path);
            int index = workbook.getSheetIndex(sheetName);
            workbook.removeSheetAt(index);

            workbook.write(fileOut);
            fileOut.close();
            if(!isSheetExist(sheetName))
                return true;
            else
                return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /************ Creates a new Column in existing sheet - returns true if successful */
    public boolean addColumn(String sheetName, String columnName) {
        /* Verify if sheet exists
        *  Get total number of column in a sheet
        *  Add a new column after the lastColumn from the sheet
        *  Write the data into file using FileOutputStream */

        try{
            if(!isSheetExist(sheetName))
                return false;

            fos = new FileOutputStream(path);
            sheet = workbook.getSheet(sheetName);
            row = sheet.getRow(0);

            int colCount = row.getLastCellNum();
            cell = row.createCell(colCount);

            cell.setCellValue(columnName);
            workbook.write(fos);
            fos.close();

            return true;
        }catch (IOException e){
            return false;
        }
    }

    /************ Removes the mentioned Column from existing sheet - returns true if successful */
    public boolean removeColumn(String sheetName, String columnName) {
        /* return false (dont remove column) if sheet doesn't  exists
        *  get the total rows in the sheet
        *  get the column no. based on column Name
        *  iterate over each & every row for derived col no. > delete the cell value there
        *   write the data to file  */

        if(!removeSheet(sheetName))
            return false;

        int totalRows = getRowCount(sheetName);

        if(totalRows == 0)
            return false;

        //find out column number first
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(0);
        int colNumber = -1;
        for (int i = 0; i < row.getLastCellNum(); i++) {
            String colValue = row.getCell(i).getStringCellValue().trim();
            if (colValue.equalsIgnoreCase(columnName.trim())) {
                colNumber = i;
                break;
            }
        }

        try {
            for (int i = 0; i < totalRows; i++) {
                row = sheet.getRow(i);
                cell = row.getCell(colNumber);

                row.removeCell(cell);
            }

            fos = new FileOutputStream(path);
            workbook.write(fos);
            fos.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /************ Finds whether sheet does exist or not ************/
    public boolean isSheetExist(String sheetName) {
        int index = workbook.getSheetIndex(sheetName);
        if(index == -1){
            return false;
        } else {
            return true;
        }
    }

    /************ Returns cell row number ************/
    public int getCellRowNumber(String sheeName, String colName, String cellValue) {


        return -1;
    }

    /* LOCAL PRACTICE*/
    public List<List<String>> excelRead(String sheetName) {
        try {
            fis = new FileInputStream(path);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet(sheetName);

        } catch (FileNotFoundException e1) {

        } catch (IOException e2) {

        }

        int numberOfRows = sheet.getLastRowNum() - sheet.getFirstRowNum();
        int numberOfCells = sheet.getRow(0).getLastCellNum();
        DataFormatter formatter = new DataFormatter();

        //Iterating over each row & cell using the no. of rows & cell
        for (int i = 0; i <= numberOfRows; i++) {
            row = sheet.getRow(i);
            data.add(i, new ArrayList<String>());

            for (int j = 0; j < numberOfCells; j++) {
                cell = row.getCell(j);

                if (cell != null) {
                    if (cell.getCellType() == CellType.NUMERIC) {
                        data.get(i).add(j, String.valueOf(formatter.formatCellValue(cell)));
                    } else {
                        data.get(i).add(j, cell.getStringCellValue());
                    }
                }
            }
        }
        return data;
    }

    // TODO
    public void readExcelData() throws IOException {
        String sheetName = "DataTable1";

        fis = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetName);

        //Iterate over each row & cell using Iterator class
        Iterator<Row> rowIterator = sheet.iterator();

        while (rowIterator.hasNext()) {
            row = rowIterator.next();
            System.out.println("------Next Row----------");
            Iterator<Cell> cellIterator = row.iterator();
            {
                while (cellIterator.hasNext()) {
                    cell = cellIterator.next();

                    DataFormatter formatter = new DataFormatter();
                    String cellValue = formatter.formatCellValue(cell);
                    System.out.println(cellValue);
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {

        ExcelReader reader = new ExcelReader(filePath);
//        reader.readExcelData();
//        reader.excelRead("DataTable1");
//        System.out.println(reader.data);
//        System.out.println("END");

        String value = reader.getCellData("DataTable1",2,"Date");
        System.out.println("Cell value is : "+ value);

        System.out.println("no. of rows: "+ reader.getRowCount("DataTable1"));
        System.out.println("no. of columns: "+ reader.getColumnCount("DataTable1"));
    }

}
