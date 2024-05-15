package com.mb.cap.blog.exception;

public enum ErrorCode {
    EMAIL_REGISTERED("CL0001"),
    USERNAME_REGISTERED("CL0002"),
    UNAUTHORIZED("CL401"),
    NOT_FOUND("CL404"),
    BAD_REQUEST("CL400");

    public final String value;

    private ErrorCode(String value) {
        this.value = value;
    }
}
