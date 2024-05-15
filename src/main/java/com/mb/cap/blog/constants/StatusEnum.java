package com.mb.cap.blog.constants;

import lombok.Getter;

@Getter
public enum StatusEnum {
    ACTIVE(true)
    , INACTIVE(false);

    public final boolean value;

    private StatusEnum(boolean value) {
        this.value = value;
    }
}
