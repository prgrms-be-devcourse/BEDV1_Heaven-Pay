package com.programmers.heavenpay.store.service;

import com.programmers.heavenpay.store.converter.StoreConverter;
import com.programmers.heavenpay.store.dto.StoreCreateRequest;
import com.programmers.heavenpay.store.entity.Store;
import com.programmers.heavenpay.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final StoreConverter storeConverter;

    @Transactional(readOnly = true)
    public Long create(StoreCreateRequest storeCreateRequest) {
        Store store = storeConverter.toStoreEntity(storeCreateRequest);
        storeRepository.save(store);

        return store.getId();
    }
}
