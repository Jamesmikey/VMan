package tz.ac.iact.va.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
public class ForgotPasswordRequest {

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Invalid email")
    private String email;

}