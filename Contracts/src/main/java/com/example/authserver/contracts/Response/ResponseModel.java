package com.example.authserver.contracts.Response;

import org.springframework.http.HttpStatus;

public class ResponseModel<T>
{
    private String message;
    private T data;
    private HttpStatus httpStatus;



    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

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
