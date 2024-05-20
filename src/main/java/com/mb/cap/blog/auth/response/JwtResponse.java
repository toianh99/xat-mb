package com.mb.cap.blog.auth.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String refreshToken;
    private String id;
    private String username;
    private String email;
    private List<String> roles;

    public JwtResponse(String jwt, String token, String id, String username, String email, List<String> roles) {
        this.token = jwt;
        this.refreshToken = token;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}
