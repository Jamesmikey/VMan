package tz.ac.iact.va.dto.assignment;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tz.ac.iact.va.dto.ward.RefWardDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAssignmentDTO {

    @NotNull(message = "Must specify the ward")
    private RefWardDTO ward;

}
