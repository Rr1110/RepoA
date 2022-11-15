package com.thoughtworks.repospring.common;

import lombok.Builder;

@Builder
public class ErrorResult {

    private int code;
    private String message;

    public int getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
