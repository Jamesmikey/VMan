package tz.ac.iact.va.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserDTO {


    @NotEmpty(message = "Must provide first name")
    private String firstName;

    @NotEmpty(message = "Must provide second name")
    private String secondName;

    @NotEmpty(message = "Must provide last name")
    private String lastName;

    @NotEmpty(message = "Must provide phone number")
    private String phoneNumber;


    @NotEmpty(message = "Must provide sex")
    private String sex;

}
