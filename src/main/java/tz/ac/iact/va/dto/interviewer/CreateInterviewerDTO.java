package tz.ac.iact.va.dto.interviewer;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tz.ac.iact.va.dto.user.RefUserDTO;
import tz.ac.iact.va.dto.ward.RefWardDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateInterviewerDTO {

    @NotNull(message = "Must specify the ward")
    private RefWardDTO ward;

    @NotNull(message = "Must specify the user")
    private RefUserDTO user;

}
