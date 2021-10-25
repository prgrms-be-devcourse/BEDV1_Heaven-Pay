package com.programmers.heavenpay.store.dto;

import com.programmers.heavenpay.store.entity.Store;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class StoreCreateRequest {
    @NotBlank(message = "store name must not be null or blank")
    private String name;
    @NotNull(message = "stor name must not be null")
    private Store.StoreType type;
    @NotBlank(message = "store vendor code must not be null or blank")
    private String vendorCode;
}
