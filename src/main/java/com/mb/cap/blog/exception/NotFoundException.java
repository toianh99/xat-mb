package com.mb.cap.blog.exception;

import lombok.Getter;

public class NotFoundException extends RuntimeException {
    @Getter
    private ErrorCode errorCode = ErrorCode.NOT_FOUND;

    public NotFoundException(String message) {
        super(message);
    }

    public static NotFoundException of(String message) {
        return new NotFoundException(message);
    }
}