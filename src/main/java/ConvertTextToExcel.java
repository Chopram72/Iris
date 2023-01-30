import java.io.*;

import  org.apache.poi.hssf.usermodel.HSSFSheet;
import  org.apache.poi.hssf.usermodel.HSSFWorkbook;
import  org.apache.poi.hssf.usermodel.HSSFRow;

public class ConvertTextToExcel {
    public static void main(String[] args) {
        String inputFilePath = "/Users/manikchopra/Documents/IrisCode/Input.txt";
        String outputFilename = "/Users/manikchopra/Documents/IrisCode//NewExcelFile.xls" ;
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(inputFilePath));
            String line = reader.readLine();
            HSSFWorkbook workbook = new HSSFWorkbook();
            while(line!=null) {
                String sheetName = line.split("->")[0];
                HSSFSheet sheet = workbook.createSheet(sheetName);
                int i =0;
                while(!line.contains("**************")) {
                    if(line.contains("com.devexperts.tos")) {
                        HSSFRow row = sheet.createRow(i);
                        String[] values = line.split("->");
                        String value = values[1].split(" ")[1];
                        row.createCell(0).setCellValue(value);
                        i++;
                    }

                    line = reader.readLine();
                }
                line = reader.readLine();
            }
            FileOutputStream fileOut = new FileOutputStream(outputFilename);
            workbook.write(fileOut);
            fileOut.close();
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
