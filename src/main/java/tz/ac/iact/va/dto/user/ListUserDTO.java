package tz.ac.iact.va.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListUserDTO {

    private String id;

    private String firstName;

    private String secondName;

    private String lastName;

    private String phoneNumber;

    private String fullName;

    private String sex;

    private String email;

}
