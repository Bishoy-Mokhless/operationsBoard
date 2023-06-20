package com.kazyon.operationsBoard.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReader {
    public static void readExcelFile(String filePath) throws IOException {
        // Create a file object from the given file path
        File file = new File(filePath);

        // Create an input stream to read from the file
        FileInputStream inputStream = new FileInputStream(file);

        // Create a workbook object from the input stream
        Workbook workbook = WorkbookFactory.create(inputStream);

        // Get the first sheet in the workbook
        Sheet sheet = workbook.getSheetAt(0);

        // Loop through each row in the sheet and print its contents
        for (Row row : sheet) {
            for (Cell cell : row) {
                System.out.print(cell.toString() + "\t");
            }
            System.out.println();
        }

        // Close the workbook and input stream
        workbook.close();
        inputStream.close();
    }
}