package tz.ac.iact.va.dto.auth;

import lombok.Data;
import tz.ac.iact.va.model.User;

@Data
public class LoginResponse {

    private String token;

    private String username;

    private long expiresIn;

    private String name;
}
