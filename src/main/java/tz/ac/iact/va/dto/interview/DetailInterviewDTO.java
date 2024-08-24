package tz.ac.iact.va.dto.interview;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tz.ac.iact.va.dto.interviewer.ListInterviewerDTO;
import tz.ac.iact.va.dto.interviewer.RefInterviewerDTO;
import tz.ac.iact.va.dto.notification.ListNotificationDTO;
import tz.ac.iact.va.dto.notification.RefNotificationDTO;
import tz.ac.iact.va.dto.user.ListUserDTO;
import tz.ac.iact.va.dto.ward.ListWardDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailInterviewDTO {

    private ListInterviewerDTO interviewer;

    private ListNotificationDTO notification;

}
