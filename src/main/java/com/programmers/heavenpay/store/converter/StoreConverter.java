package com.programmers.heavenpay.store.converter;

import com.programmers.heavenpay.store.dto.StoreCreateRequest;
import com.programmers.heavenpay.store.dto.StoreInfoResponse;
import com.programmers.heavenpay.store.entity.Store;
import org.springframework.stereotype.Component;

@Component
public class StoreConverter {
    public Store toStoreEntity(StoreCreateRequest storeCreateRequest) {
        return Store.builder()
                .name(storeCreateRequest.getName())
                .type(storeCreateRequest.getType())
                .vendorCode(storeCreateRequest.getVendorCode())
                .build();
    }

    public StoreInfoResponse toStoreInfoResponse(Store store) {
        return StoreInfoResponse.builder()
                .name(store.getName())
                .type(store.getType())
                .vendorCode(store.getVendorCode())
                .build();
    }
}
