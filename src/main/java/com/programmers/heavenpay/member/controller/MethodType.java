package com.programmers.heavenpay.member.controller;

import lombok.Getter;

@Getter
public enum MethodType {
    READ("get"),
    READ_ALL("get-all"),
    CREATE("create"),
    POST("post"),
    UPDATE("update"),
    DELETE("delete");

    private final String typeStr;

    MethodType(String typeStr) {
        this.typeStr = typeStr;
    }
}
