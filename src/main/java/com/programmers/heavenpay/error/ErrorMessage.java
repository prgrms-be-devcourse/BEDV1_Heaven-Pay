package com.programmers.heavenpay.error;

import com.programmers.heavenpay.error.exception.NotDefinitionException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

@Getter
public enum ErrorMessage {
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "정의되지 않은 서버 에러"),
    NOT_EXIST_MEMBER_ID(HttpStatus.NOT_FOUND, "존재하지 않는 Member Id");

    private final HttpStatus status;
    private final String message;

    ErrorMessage(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public static ErrorMessage of(String errorMessage) {
        return Arrays.stream(values())
                .filter(e -> e.message.equals(errorMessage))
                .findFirst()
                .orElseThrow(() -> new NotDefinitionException(ErrorMessage.INTERNAL_SERVER_ERROR));
    }
}
