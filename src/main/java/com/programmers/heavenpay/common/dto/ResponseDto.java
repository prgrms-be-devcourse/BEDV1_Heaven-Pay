package com.programmers.heavenpay.common.dto;

import lombok.Getter;
import lombok.NonNull;
import org.springframework.hateoas.EntityModel;

import java.time.LocalDateTime;

@Getter
public class ResponseDto<T> {
    @NonNull
    private final int status;
    @NonNull
    private final String message;
    @NonNull
    private final LocalDateTime serverDateTime;
    @NonNull
    private final T data;
    @NonNull
    private final T link;

    private ResponseDto(ResponseMessage message, T data, T link) {
        this.status = message.getStatus().value();
        this.message = message.name();
        this.serverDateTime = LocalDateTime.now();
        this.data = data;
        this.link = link;
    }

    public static <T> ResponseDto of(ResponseMessage message, T data, T link) {
        return new ResponseDto(message, data, link);
    }

    public static <T> ResponseDto of(ResponseMessage message, EntityModel<T> entityModel) {
        return new ResponseDto(message, entityModel.getContent(), entityModel.getLinks());
    }
}