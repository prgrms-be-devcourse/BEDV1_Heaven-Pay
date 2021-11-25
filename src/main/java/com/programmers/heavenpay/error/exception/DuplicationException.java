package com.programmers.heavenpay.error.exception;

import com.programmers.heavenpay.error.ErrorMessage;

public class DuplicationException extends BusinessException {
    public DuplicationException(ErrorMessage message) {
        super(message);
    }
}