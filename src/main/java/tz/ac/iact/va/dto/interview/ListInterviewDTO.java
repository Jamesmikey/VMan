package tz.ac.iact.va.dto.interview;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tz.ac.iact.va.dto.interviewer.ListInterviewerDTO;
import tz.ac.iact.va.dto.notification.ListNotificationDTO;
import tz.ac.iact.va.dto.user.ListUserDTO;
import tz.ac.iact.va.dto.ward.ListWardDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListInterviewDTO {

    private ListInterviewerDTO interviewer;

    private ListNotificationDTO notification;
}
