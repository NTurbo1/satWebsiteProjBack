package com.nturbo1.satWebsiteProjBack.web.exception.handler;

public class ApiVersionNotSupportedException extends RuntimeException{

    public ApiVersionNotSupportedException(final String errorMessage) {
        super(errorMessage);
    }
}
