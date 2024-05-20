package com.mb.cap.blog.auth.jwt;

import com.mb.cap.blog.auth.entity.AccessToken;
import com.mb.cap.blog.auth.service.AccessTokenService;
import com.mb.cap.blog.auth.service.CustomUserDetailsService;
import com.mb.cap.blog.auth.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Date;

@Component
public class AuthTokenFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private AccessTokenService accessTokenService;
    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

    public AuthTokenFilter() {
    }

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        try {
            Date t1 = new Date();
            String jwt = this.parseJwt(request);
            if (jwt != null && this.jwtUtils.validateJwtToken(jwt)) {
                AccessToken accessToken = this.accessTokenService.findByToken(jwt);
                if (accessToken != null) {
                    String username = this.jwtUtils.getUserNameFromJwtToken(jwt);
                    this.jwtUtils.getIdFromJwtToken(jwt);
                    UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, (Object)null, userDetails.getAuthorities());
                    authentication.setDetails((new WebAuthenticationDetailsSource()).buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }

            Date t2 = new Date();
            long t = t2.getTime() - t1.getTime();
            logger.info("time for doFilterInternal {}", t);
        } catch (Exception var11) {
            logger.error("Cannot set user authentication: {}", var11.getMessage());
        }

        filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        return StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ") ? headerAuth.substring(7, headerAuth.length()) : null;
    }
}