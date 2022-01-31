package com.mkyong.exception;

public class CustomException extends RuntimeException {

    private final int code;

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    private final String message;

    public CustomException(String message, int code) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
