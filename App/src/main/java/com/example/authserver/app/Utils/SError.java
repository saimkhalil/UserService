package com.example.authserver.app.Utils;

import org.springframework.http.HttpStatus;

public class SError extends Exception
{
    private String message;
    private HttpStatus httpStatus;

    public SError(String message, HttpStatus httpStatus)
    {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
