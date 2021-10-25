package com.programmers.heavenpay.error;

import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDateTime;

@Getter
public class ErrorResponseDto {
    @NonNull
    private final int status;
    @NonNull
    private final String message;
    @NonNull
    private final LocalDateTime serverDateTime;

    private ErrorResponseDto(ErrorMessage message) {
        this.status = message.getStatus().value();
        this.message = message.name();
        this.serverDateTime = LocalDateTime.now();
    }

    public static ErrorResponseDto of(ErrorMessage message) {
        return new ErrorResponseDto(message);
    }
}