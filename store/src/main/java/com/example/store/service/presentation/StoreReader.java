package com.example.store.service.presentation;

import com.example.store.domain.Store;
import com.example.store.domain.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreReader {

    private final StoreRepository storeRepository;

    public Store findByStoreId(Long storeId) {
        return storeRepository.findByStoreId(storeId);
    }

    public Store findByUserId(Long userId) {
        return storeRepository.findByUserId(userId);
    }

    public List<Store> findAllByStoreName(String storeName) {
        return storeRepository.findAllByStoreName(storeName);
    }

}
