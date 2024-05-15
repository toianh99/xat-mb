package com.mb.cap.blog.exception;

import lombok.Getter;

public class UnauthorizedException extends RuntimeException {
    @Getter
    private ErrorCode errorCode = ErrorCode.UNAUTHORIZED;

    public UnauthorizedException(String message) {
        super(message);
    }

    public static UnauthorizedException of(String message) {
        return new UnauthorizedException(message);
    }
}
