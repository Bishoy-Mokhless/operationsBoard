package com.kazyon.operationsBoard;

import com.kazyon.operationsBoard.Stores.Model.Stores;
import com.kazyon.operationsBoard.Stores.Service.StoresServiceImpl;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class OperationsBoardApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(OperationsBoardApplication.class, args);
		/*Workbook workbook = new XSSFWorkbook("D:\\KPlus\\Code\\test.xlsx");
		StoresServiceImpl service = new StoresServiceImpl();
		System.out.println(service.getStoresFromExcel(workbook));
*/
	}


}
