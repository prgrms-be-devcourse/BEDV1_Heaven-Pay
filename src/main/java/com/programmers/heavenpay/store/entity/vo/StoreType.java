package com.programmers.heavenpay.store.entity.vo;

import com.programmers.heavenpay.error.ErrorMessage;
import com.programmers.heavenpay.error.exception.NotExistsException;

import java.util.Arrays;

public enum StoreType {
    FOOD,
    DEPARTMENT_STORE,
    LIVING,
    BAKERY,
    ELECTRONIC,
    COSMETICS;

    public static StoreType getValue(String typeStr) {
        return Arrays.stream(StoreType.values())
                .filter(v -> v.toString().equals(typeStr))
                .findFirst()
                .orElseThrow(() -> new NotExistsException(ErrorMessage.NOT_EXIST_STORE_TYPE));
    }
}
