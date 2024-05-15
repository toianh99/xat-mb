package com.mb.cap.blog.exception;

import lombok.Getter;

public class ValidationException extends RuntimeException {
    @Getter
    private ErrorCode errorCode;

    public ValidationException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public static ValidationException of(ErrorCode errorCode, String message) {
        return new ValidationException(errorCode, message);
    }
}