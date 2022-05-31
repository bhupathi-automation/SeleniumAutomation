package utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class ExcelWritter {

    public void writeDataIntoExcel() throws IOException {
        String fileName = "D:\\workspace\\Test1.xlsx";
        OutputStream writeFile = new FileOutputStream(fileName);

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Test");
        Row row;
        Cell cell;

        /* Simple way of Writing data into excel */
//        row = sheet.createRow(0);
//        cell = row.createCell(0);
//        cell.setCellValue("Testing");
//        workbook.write(writeFile); // this writes the excel file from JVM to HardDisk

        /* creating hard coded data using "TreeMap" to write in Excel */
        Map<String, Object[]> dataSet = new TreeMap<String, Object[]>();

        dataSet.put("1", new Object[]{"emp_id", "name", "dept"});
        dataSet.put("2", new Object[]{"101", "John", "Accounts"});
        dataSet.put("3", new Object[]{"102", "Mark", "Sales"});
        dataSet.put("4", new Object[]{"103", "Luke", "Marketing"});

        Set<String> keys = dataSet.keySet();
        int rownum = 0;

        for (String key : keys) {
            Object[] values = dataSet.get(key);
            row = sheet.createRow(rownum++);
            int cellnum = 0;

            for (Object data : values) {

                cell = row.createCell(cellnum++);
                if (data instanceof String) {
                    cell.setCellValue((String) data);
                } else if (data instanceof Integer) {
                    cell.setCellValue((Integer) data);
                }

            }
        }
        workbook.write(writeFile);
        writeFile.close();
        System.out.println("******* Data has been written to excel file *******");
    }

    public static void main(String[] args) throws IOException {
        ExcelWritter writer = new ExcelWritter();
        writer.writeDataIntoExcel();
    }
}
