package com.mb.cap.blog.auth.filler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;
import org.apache.catalina.connector.RequestFacade;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Integer.MIN_VALUE)
public class JsonToUrlEncodedAuthenticationFilter implements Filter {
    public JsonToUrlEncodedAuthenticationFilter() {
    }

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (!(request instanceof RequestFacade)) {
            chain.doFilter(request, response);
        } else {
            if (Objects.equals(request.getContentType(), "application/json") && Objects.equals(((RequestFacade)request).getServletPath(), "/oauth/token")) {
                InputStream is = request.getInputStream();
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                byte[] data = new byte[16384];

                int nRead;
                while((nRead = is.read(data, 0, data.length)) != -1) {
                    buffer.write(data, 0, nRead);
                }

                buffer.flush();
                byte[] json = buffer.toByteArray();
                HashMap<String, String> result = (HashMap)(new ObjectMapper()).readValue(json, HashMap.class);
                HashMap<String, String[]> r = new HashMap();
                Iterator var11 = result.keySet().iterator();

                while(var11.hasNext()) {
                    String key = (String)var11.next();
                    String[] val = new String[]{(String)result.get(key)};
                    r.put(key, val);
                }

                String[] val = new String[]{((RequestFacade)request).getMethod()};
                r.put("_method", val);
                HttpServletRequest s = new CustomServletRequestWrapper((HttpServletRequest)request, r);
                chain.doFilter(s, response);
            } else {
                chain.doFilter(request, response);
            }

        }
    }

    public void destroy() {
    }
}

