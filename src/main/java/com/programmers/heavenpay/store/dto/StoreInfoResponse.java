package com.programmers.heavenpay.store.dto;

import com.programmers.heavenpay.store.entity.Store;
import lombok.Builder;

@Builder
public class StoreInfoResponse {
    private String name;
    private Store.StoreType type;
    private String vendorCode;
}
