package com.example.store.application.service;

import com.example.store.application.domain.Store;
import com.example.store.application.presentation.dto.req.StoreInput;
import com.example.store.application.service.presentation.StoreReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QueryStoreService {

    private final StoreReader storeReader;

    public Store getStore(StoreInput storeInput) {
        return storeReader.findByStoreId(storeInput.storeId());
    }

    public Store getStoreByUser(StoreInput storeInput) {
        return storeReader.findByStoreId(storeInput.storeId());
    }

    public List<Store> getStoreByName(StoreInput storeInput) {
        return storeReader.findAllByStoreName(storeInput.storeName());
    }

}
