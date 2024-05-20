package com.mb.cap.blog.auth.filler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

import java.util.HashMap;
import java.util.Map;

public class CustomHttpServletRequest extends HttpServletRequestWrapper {
    private final Map<String, String[]> additionalParams;
    private final HttpServletRequest request;

    public CustomHttpServletRequest(final HttpServletRequest request, final Map<String, String[]> additionalParams) {
        super(request);
        this.request = request;
        this.additionalParams = additionalParams;
    }

    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> map = this.request.getParameterMap();
        Map<String, String[]> param = new HashMap();
        param.putAll(map);
        param.putAll(this.additionalParams);
        return param;
    }
}

