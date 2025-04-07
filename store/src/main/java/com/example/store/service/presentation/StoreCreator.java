package com.example.store.service.presentation;

import com.example.store.domain.Store;
import com.example.store.domain.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreCreator {

    private final StoreRepository storeRepository;

    public Store save(Store store) {
        return storeRepository.save(store);
    }

}
