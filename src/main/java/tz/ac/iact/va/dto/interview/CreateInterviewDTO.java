package tz.ac.iact.va.dto.interview;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tz.ac.iact.va.dto.assignment.RefAssignmentDTO;
import tz.ac.iact.va.dto.notification.RefNotificationDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateInterviewDTO {

    @NotNull(message = "Must specify the interviewer")
    private RefAssignmentDTO assignment;

    @NotNull(message = "Must specify the notification")
    private RefNotificationDTO notification;

}
