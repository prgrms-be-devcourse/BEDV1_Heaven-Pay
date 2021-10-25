package com.programmers.heavenpay.store.entity.vo;

import com.programmers.heavenpay.error.ErrorMessage;
import com.programmers.heavenpay.error.exception.NotExistsException;

public enum StoreType {
    FOOD,
    DEPARTMENT_STORE,
    LIVING,
    BAKERY,
    ELECTRONIC,
    COSMETICS;

    public static StoreType getValue(String typeStr) {
        try {
            return StoreType.valueOf(typeStr);
        } catch (IllegalArgumentException e) {
            throw new NotExistsException(ErrorMessage.NOT_EXIST_STORE_TYPE_EXCEPTION);
        }
    }
}
