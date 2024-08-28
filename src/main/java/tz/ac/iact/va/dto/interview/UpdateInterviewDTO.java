package tz.ac.iact.va.dto.interview;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tz.ac.iact.va.dto.assignment.RefAssignmentDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateInterviewDTO {

    @NotNull(message = "Must specify the assignment")
    private RefAssignmentDTO assignment;

}
