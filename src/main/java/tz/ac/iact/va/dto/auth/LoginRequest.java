package tz.ac.iact.va.dto.auth;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginRequest {

    @NotEmpty(message = "Provide username")
    private String username;

    @NotEmpty(message = "Provide password")
    private String password;
}
