package com.kazyon.operationsBoard.Stores.Service;

import com.kazyon.operationsBoard.Stores.Model.Stores;
import com.kazyon.operationsBoard.Stores.Repo.StoresRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
@Slf4j
public class StoresServiceImpl implements StoresService{
    @Autowired
    private  StoresRepository storesRepository;


    @Override
    public Stores addStore(Stores store) {
        return storesRepository.save(store);
    }

    @Override
    public Stores updateStore(Stores store, int storeId) throws Exception {
        Optional<Stores> optionalStores = storesRepository.findById(storeId);
        if(!optionalStores.isPresent()) {
            throw new Exception("STORE NOT FOUND");
        }
        Stores storeToUpdate = optionalStores.get();
        storeToUpdate.setAddress(store.getAddress());
        storeToUpdate.setGovernorate(store.getGovernorate());
        storeToUpdate.setName(store.getName());
        storeToUpdate.setLatitude(storeToUpdate.getLatitude());
        storeToUpdate.setLongitude(storeToUpdate.getLongitude());
        storeToUpdate.setStatus(store.getStatus());

        store = storesRepository.save(storeToUpdate);
        return store;
    }

    @Override
    public List<Stores> viewAllStores() {
        return storesRepository.findAll();
    }

    @Override
    public Stores getStoreByID(int storeId) throws Exception {
        Optional<Stores> optionalStores = storesRepository.findById(storeId);
        if(!optionalStores.isPresent()) {
            throw new Exception("STORE NOT FOUND");
        }
        Stores stores = optionalStores.get();
        return stores;
    }

    @Override
    public Stores getStoreByName(String storeName) throws Exception {
        Optional<Stores> optionalStores = storesRepository.findByName(storeName);
        if(!optionalStores.isPresent()) {
            throw new Exception("STORE NOT FOUND");
        }
        Stores stores = optionalStores.get();
        return stores;
    }

    @Override
    public void clearStores() {
        storesRepository.deleteAll();

    }

    @Override
    public List<Stores> getStoresFromExcel(Workbook workbook) {
        clearStores();
        List<Stores> stores = new ArrayList<>();
        Stores tempStore;
        Sheet sheet = workbook.getSheetAt(0);

        sheet.removeRow(sheet.getRow(0));
        for(Row row : sheet) {
            if(row.getRowNum() == 0) {
                continue;
            }
            tempStore = new Stores();
            for (int i = 0; i < 6; i++) {
                Cell cell = row.getCell(i);
                if(cell == null) {
                    continue;
                }
                if(i == 0) {
                    tempStore.setName(cell.getStringCellValue());
                } else if(i == 1) {
                    tempStore.setAddress(cell.getStringCellValue());
                } else if(i == 2) {
                    tempStore.setGovernorate(cell.getStringCellValue());
                } else if(i == 3) {
                    tempStore.setLongitude((float) cell.getNumericCellValue());
                } else if(i == 4) {

                    tempStore.setLatitude((float) cell.getNumericCellValue());
                } else if(i == 5) {
                    tempStore.setStatus(cell.getStringCellValue());
                }
            }
            stores.add(tempStore);
        }
        if (stores.size()>0)
            saveAll(stores);
        return stores;
    }

    @Override
    public void saveAll(List<Stores> stores) {
        storesRepository.saveAll(stores);

    }

}
