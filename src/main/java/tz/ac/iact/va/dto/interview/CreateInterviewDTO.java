package tz.ac.iact.va.dto.interview;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tz.ac.iact.va.dto.interviewer.RefInterviewerDTO;
import tz.ac.iact.va.dto.notification.RefNotificationDTO;
import tz.ac.iact.va.dto.user.RefUserDTO;
import tz.ac.iact.va.dto.ward.RefWardDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateInterviewDTO {

    @NotNull(message = "Must specify the interviewer")
    private RefInterviewerDTO interviewer;

    @NotNull(message = "Must specify the notification")
    private RefNotificationDTO notification;

}
