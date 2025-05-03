package com.example.store.application.service.presentation;

import com.example.store.application.domain.Store;
import com.example.store.application.domain.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreDeleter {

    private final StoreRepository storeRepository;

    public Store delete(Store store) {
        storeRepository.delete(store);
        return store;
    }

}
