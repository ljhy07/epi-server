package com.example.store.service;

import com.example.store.domain.Store;
import com.example.store.presentation.dto.req.StoreInput;
import com.example.store.service.presentation.StoreCreator;
import com.example.store.service.presentation.StoreDeleter;
import com.example.store.service.presentation.StoreReader;
import com.example.store.service.presentation.StoreUpdater;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MutationStoreService {

    private final StoreCreator storeCreator;
    private final StoreUpdater storeUpdater;
    private final StoreDeleter storeDeleter;
    private final StoreReader storeReader;

    public Store create(StoreInput storeInput){
        Long userId = null;

        return storeCreator.save(
                Store.createStoreBuilder()
                        .userId(userId)
                        .storeName(storeInput.storeName())
                        .storeAddress(storeInput.storeAddress())
                        .storeDescription(storeInput.storeDescription())
                        .build()
        );
    }

    public Store update(StoreInput storeInput){
        Long userId = null;

        return storeUpdater.update(
                storeReader.findByUserId(userId),
                Store.updateStoreBuilder()
                        .storeName(storeInput.storeName())
                        .storeAddress(storeInput.storeAddress())
                        .storeDescription(storeInput.storeDescription())
                        .build()
        );
    }

    public Store delete(){
        Long userId = null;

        return storeDeleter.delete(storeReader.findByUserId(userId));
    }

}
