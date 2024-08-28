package tz.ac.iact.va.dto.assignment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tz.ac.iact.va.dto.user.ListUserDTO;
import tz.ac.iact.va.dto.ward.ListWardDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatedAssignmentDTO {

    private ListWardDTO ward;

    private ListUserDTO user;
}
