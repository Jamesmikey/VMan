package tz.ac.iact.va.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author James S. Michael
 * @created 20-09-2022 01:53:07
 */

@Data
@NoArgsConstructor
public class PasswordChange {

    @NotBlank(message = "First name is mandatory")
    private String firstName;

//    @NotBlank(message = "Second name is mandatory")
    private String secondName;

    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    @NotBlank(message = "PhoneNumber is mandatory")
    private String phoneNumber;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Invalid email")
    private String email;

    @NotBlank(message = "Country is mandatory")
    private String country;

}
