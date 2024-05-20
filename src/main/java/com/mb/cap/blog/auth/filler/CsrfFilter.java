package com.mb.cap.blog.auth.filler;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import java.io.IOException;

public class CsrfFilter extends OncePerRequestFilter {
    private final Log log = LogFactory.getLog(this.getClass());
    public static final String XSRF_TOKEN_COOKIE_NAME = "XSRF-TOKEN";
    public static final String XSRF_TOKEN_HEADER_NAME = "X-XSRF-TOKEN";

    public CsrfFilter() {
    }

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        this.log.debug("Inside csrfFilter ...");
        CsrfToken csrf = (CsrfToken)request.getAttribute(CsrfToken.class.getName());
        if (csrf != null) {
            Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
            String token = csrf.getToken();
            if (cookie == null || token != null && !token.equals(cookie.getValue())) {
                cookie = new Cookie("XSRF-TOKEN", token);
                cookie.setPath("/");
                response.addCookie(cookie);
                response.addHeader("X-XSRF-TOKEN", token);
            }
        }

        filterChain.doFilter(request, response);
    }
}