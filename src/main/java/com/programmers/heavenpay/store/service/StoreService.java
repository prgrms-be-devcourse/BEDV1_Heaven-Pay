package com.programmers.heavenpay.store.service;

import com.programmers.heavenpay.store.converter.StoreConverter;
import com.programmers.heavenpay.store.dto.StoreCreateRequest;
import com.programmers.heavenpay.store.dto.StoreInfoResponse;
import com.programmers.heavenpay.store.dto.StoreUpdateRequest;
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

    @Transactional
    public void update(StoreUpdateRequest storeUpdateRequest) throws Exception {
        Store store = storeRepository.findById(storeUpdateRequest.getId())
                .orElseThrow(() -> new Exception("store not found"));

        store.changeName(storeUpdateRequest.getName());
        store.changeType(storeUpdateRequest.getType());
        store.changeVendorCode(storeUpdateRequest.getVendorCode());
    }

    @Transactional
    public StoreInfoResponse findByName(String name) throws Exception {
        Store store = storeRepository.findByName(name)
                .orElseThrow(() -> new Exception("store not found"));

        return storeConverter.toStoreInfoResponse(store);
    }

    @Transactional
    public void delete(Long id) throws Exception {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new Exception("store not found"));

        storeRepository.delete(store);
    }
}
