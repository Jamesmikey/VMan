package tz.ac.iact.va.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author James S. Michael
 * @created 20-09-2022 01:53:07
 */

@Data
@NoArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "First name is mandatory")
    private String firstName;


    private String secondName;

    @NotBlank(message = "Last name is mandatory")
    private String lastName;
    @NotBlank(message = "PhoneNumber is mandatory")
    private String phoneNumber;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Invalid email")
    private String email;


    @NotBlank(message = "Password is mandatory")
    @Size(min = 8,message = "Password must contain at least 8 characters")
    private String password;

    private String country;

    @NotBlank(message = "reCAPTCHA response is required")
    private String reCaptchaResponse;

}
