package tz.ac.iact.va.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ResetPasswordRequest {

    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotBlank(message = "reCAPTCHA response is required")
    private String reCaptchaResponse;


}