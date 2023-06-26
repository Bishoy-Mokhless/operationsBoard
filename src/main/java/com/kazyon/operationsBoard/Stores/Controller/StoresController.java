package com.kazyon.operationsBoard.Stores.Controller;

import com.kazyon.operationsBoard.Stores.Model.Stores;
import com.kazyon.operationsBoard.Stores.Service.StoresService;
import com.kazyon.operationsBoard.config.Response;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/v1/stores")
@RequiredArgsConstructor
@CrossOrigin(origins ={"http://localhost:4200/", "https://legalsystem.netlify.app/"} ,
        methods = {RequestMethod.GET,RequestMethod.DELETE,
                RequestMethod.PUT,RequestMethod.HEAD,RequestMethod.OPTIONS,
                RequestMethod.POST},
        allowedHeaders = {"*"})
public class StoresController {
    @Autowired
    private  StoresService storesService;
    @GetMapping("/getAll")
    public ResponseEntity<Response> getStores(){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(storesService.viewAllStores())
                        .message("stores retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()

        );
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getStoreById(@PathVariable("id") Long id) throws Exception {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(storesService.getStoreByID(id.intValue()))
                        .message("store retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()

        );
    }
    @PostMapping("/add")
    public ResponseEntity<Response> addStore(@RequestBody Stores store){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(storesService.addStore(store))
                        .message("store created")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()

        );
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Response> updateProduct(@PathVariable("id") Long id,@RequestBody Stores stores) throws Exception {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(storesService.updateStore(stores, id.intValue()))
                        .message("store updated")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()

        );
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Response> deleteAllStore(){
        storesService.clearStores();
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data("stores deleted")
                        .message("Product deleted")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()

        );
    }
    @PostMapping("/upload/file")
    public ResponseEntity<?> excelFile(@RequestBody MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            return new ResponseEntity("You must select a file!", HttpStatus.OK);
        }
        try {
            String filename = file.getOriginalFilename();
            String extension = filename.substring(filename.lastIndexOf(".") + 1, filename.length());

            if(!extension.equals("xls") && !extension.equals("xlsx")){
                throw new Exception("please insert excel file");
            }
            Workbook workbook = new XSSFWorkbook(file.getInputStream());
            storesService.getStoresFromExcel(workbook);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }


}
