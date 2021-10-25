package com.programmers.heavenpay.store.service;

import com.programmers.heavenpay.error.ErrorMessage;
import com.programmers.heavenpay.error.exception.NotDefinitionException;
import com.programmers.heavenpay.error.exception.NotExistsException;
import com.programmers.heavenpay.store.converter.StoreConverter;
import com.programmers.heavenpay.store.dto.response.StoreInfoResponse;
import com.programmers.heavenpay.store.entity.Store;
import com.programmers.heavenpay.store.entity.vo.StoreType;
import com.programmers.heavenpay.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final StoreConverter storeConverter;

    @Transactional
    public Long create(String name, String typeStr, String vendorCode) {
        StoreType type = StoreType.getValue(typeStr);

        Store store = storeConverter.toStoreEntity(name, type, vendorCode);
        Store savedStore = storeRepository.save(store);

        return savedStore.getId();
    }

    @Transactional
    public void update(Long id, String name, String typeStr, String vendorCode) {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new NotExistsException(ErrorMessage.NOT_EXIST_STORE));

        StoreType type = StoreType.getValue(typeStr);
        store.changeInfo(name, type, vendorCode);
    }

    @Transactional(readOnly = true)
    public StoreInfoResponse findByName(String name) {
        Store store = storeRepository.findByName(name)
                .orElseThrow(() -> new NotExistsException(ErrorMessage.NOT_EXIST_STORE));

        return storeConverter.toStoreInfoResponse(store);
    }

    @Transactional
    public void delete(Long id) throws NotDefinitionException {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new NotExistsException(ErrorMessage.NOT_EXIST_STORE));

        storeRepository.delete(store);
    }
}
