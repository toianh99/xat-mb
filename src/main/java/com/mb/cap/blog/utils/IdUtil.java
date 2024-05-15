package com.mb.cap.blog.utils;

import java.util.UUID;

public class IdUtil {
    public static long nextId() {
        return SnowflakeId.getInstance().nextId();
    }

    public static String generateUUID() {
        return UUID.randomUUID().toString().replace("-","");
    }
}
