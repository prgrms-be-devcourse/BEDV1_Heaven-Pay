package com.programmers.heavenpay.member.controller;

public enum MethodType {
    GET("get"),
    GET_ALL("get-all"),
    POST("post"),
    UPDATE("update"),
    DELETE("delete");

    private final String typeStr;

    MethodType(String typeStr) {
        this.typeStr = typeStr;
    }
}
