package com.example.store.service.presentation;

import com.example.store.domain.Store;
import com.example.store.domain.repository.StoreRepository;
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
