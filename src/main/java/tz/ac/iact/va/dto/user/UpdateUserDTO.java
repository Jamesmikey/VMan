package tz.ac.iact.va.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tz.ac.iact.va.dto.role.RefRoleDTO;
import tz.ac.iact.va.model.Role;

import java.util.Set;

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


}
