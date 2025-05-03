package com.example.store.application.service.presentation;

import com.example.store.application.domain.Store;
import com.example.store.application.domain.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreUpdater {

    private final StoreRepository storeRepository;

    public Store update(Store updatableStore, Store store) {
        updatableStore.updateStoreBuilder()
                .storeName(store.getStoreName())
                .storeAddress(store.getStoreAddress())
                .storeDescription(store.getStoreDescription())
                .build();

        return storeRepository.save(store);
    }

}
