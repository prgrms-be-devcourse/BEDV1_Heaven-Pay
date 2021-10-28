package com.programmers.heavenpay.common.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ResponseMessage {
    RESPONSE_EXAMPLE(HttpStatus.BAD_REQUEST, "예시 데이터입니다."),
    MEMBER_INSERT_SUCCESS(HttpStatus.OK, "member 추가 성공"),
    MEMBER_UPDATE_SUCCESS(HttpStatus.OK, "member 수정 성공"),
    MEMBER_DELETE_SUCCESS(HttpStatus.OK, "member 삭제 성공");


    private final HttpStatus status;
    private final String message;

    ResponseMessage(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}