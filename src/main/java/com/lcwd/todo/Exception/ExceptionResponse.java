package com.lcwd.todo.Exception;

import org.springframework.http.HttpStatus;

public class ExceptionResponse {
    private String message;
    private boolean success;
    private HttpStatus status;

    public ExceptionResponse() {
    }

    public ExceptionResponse(String message, boolean success, HttpStatus status) {
        this.message = message;
        this.success = success;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
