package com.kazyon.operationsBoard;

import com.kazyon.operationsBoard.Stores.Model.Stores;
import com.kazyon.operationsBoard.Stores.Service.StoresServiceImpl;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;

@SpringBootApplication
@CrossOrigin(origins ={"http://localhost:4200/", "https://legalsystem.netlify.app/"})

public class OperationsBoardApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(OperationsBoardApplication.class, args);
		/*Workbook workbook = new XSSFWorkbook("D:\\KPlus\\Code\\test.xlsx");
		StoresServiceImpl service = new StoresServiceImpl();
		System.out.println(service.getStoresFromExcel(workbook));
*/
	}
	@Bean
	public WebMvcConfigurer corsConfigurer()
	{
		String[] allowDomains = new String[3];
		allowDomains[0] = "http://localhost:4200";
		allowDomains[1] = "http://localhost:8080";
		allowDomains[2]="https://legalsystem.netlify.app/";

		System.out.println("CORS configuration....");
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins(allowDomains);
			}
		};
	}


}
