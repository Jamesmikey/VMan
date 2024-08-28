package tz.ac.iact.va.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class UpdatePasswordRequest {

    @NotBlank(message = "Password is mandatory")
    private String newPassword;

    @NotBlank(message = "Token is mandatory")
    private String token;

    @NotBlank(message = "reCAPTCHA response is required")
    private String reCaptchaResponse;
}