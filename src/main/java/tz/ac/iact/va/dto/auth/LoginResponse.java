package tz.ac.iact.va.dto.auth;

import lombok.Data;

@Data
public class LoginResponse {

    private String token;

    private String username;

    private long expiresIn;
}
