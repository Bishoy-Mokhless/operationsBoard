package com.kazyon.operationsBoard.Stores.Service;

import com.kazyon.operationsBoard.Stores.Model.Stores;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

public interface StoresService {
    Stores  addStore (Stores store);
    Stores updateStore (Stores category, int storeId) throws Exception;
    List<Stores> viewAllStores ();
    Stores getStoreByID(int storeId) throws Exception;
    Stores getStoreByName(String storeName) throws Exception;
    void clearStores();
    List<Stores> getStoresFromExcel(Workbook workbook);
    void saveAll(List<Stores> stores);
}
