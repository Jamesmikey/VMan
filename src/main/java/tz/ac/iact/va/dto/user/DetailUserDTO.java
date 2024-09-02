package tz.ac.iact.va.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tz.ac.iact.va.dto.role.DetailRoleDTO;
import tz.ac.iact.va.model.Role;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailUserDTO {

    private String id;

    private String firstName;

    private String secondName;

    private String lastName;

    private String phoneNumber;

    private String fullName;

    private Set<DetailRoleDTO> roles;

    private String email;

}
