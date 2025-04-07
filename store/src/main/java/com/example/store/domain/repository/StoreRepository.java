package com.example.store.domain.repository;

import com.example.store.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    Store findByUserId(Long userId);

    Store findByStoreId(Long storeId);

    List<Store> findAllByStoreName(String storeName);
}
