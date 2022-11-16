package com.thoughtworks.repospring.common;

import lombok.Builder;

@Builder
public class ErrorResult {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
