package tz.ac.iact.va.dto.interviewer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tz.ac.iact.va.dto.user.ListUserDTO;
import tz.ac.iact.va.dto.user.RefUserDTO;
import tz.ac.iact.va.dto.ward.ListWardDTO;
import tz.ac.iact.va.dto.ward.RefWardDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatedInterviewerDTO {

    private ListWardDTO ward;

    private ListUserDTO user;
}
