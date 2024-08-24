package tz.ac.iact.va.dto.interview;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import tz.ac.iact.va.dto.interviewer.ListInterviewerDTO;
import tz.ac.iact.va.dto.notification.ListNotificationDTO;
import tz.ac.iact.va.dto.user.ListUserDTO;
import tz.ac.iact.va.dto.ward.ListWardDTO;
import tz.ac.iact.va.enums.InterviewStatus;
import tz.ac.iact.va.model.Interviewer;
import tz.ac.iact.va.model.Notification;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatedInterviewDTO {

    private ListInterviewerDTO interviewer;

    private ListNotificationDTO notification;

    private InterviewStatus status;
}
