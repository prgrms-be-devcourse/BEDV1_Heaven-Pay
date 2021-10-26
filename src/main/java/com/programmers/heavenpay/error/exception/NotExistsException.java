package com.programmers.heavenpay.error.exception;

import com.programmers.heavenpay.error.ErrorMessage;

public class NotExistsException extends RuntimeException {
    public NotExistsException(ErrorMessage message) {
        super(message.getMessage());
    }
}
