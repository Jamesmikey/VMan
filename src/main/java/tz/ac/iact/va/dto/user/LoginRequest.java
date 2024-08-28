package tz.ac.iact.va.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;



/**
 * @author James S. Michael
 * @created 20-09-2022 01:53:07
 */

@Data
@NoArgsConstructor
public class LoginRequest {
    @NotBlank(message = "Username can not be empty")
    private String username;

    @NotBlank(message = "Password can not be empty")
    private String password;
}
