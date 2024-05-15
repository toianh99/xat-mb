package com.mb.cap.blog.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> {
    private String errorCode;
    private String message;
    private T data;

    public static <T> CommonResponse<T> success(T data) {
        return new CommonResponseBuilder<T>().message("OK").data(data).build();
    }

    public static <T> CommonResponse<T> fail(String errorCode, String message, T data) {
        CommonResponseBuilder<T> builder = new CommonResponse.CommonResponseBuilder<T>();
        return builder.errorCode(errorCode).message(message).data(data).build();
    }

    public static <T> CommonResponse<T> success() {
        return new CommonResponseBuilder<T>().message("OK").build();
    }
}