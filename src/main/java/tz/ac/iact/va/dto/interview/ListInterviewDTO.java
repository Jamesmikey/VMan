package tz.ac.iact.va.dto.interview;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tz.ac.iact.va.dto.assignment.ListAssignmentDTO;
import tz.ac.iact.va.dto.notification.ListNotificationDTO;
import tz.ac.iact.va.dto.vaReport.RefVAReportDTO;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListInterviewDTO {

    private String id;

    private ListAssignmentDTO assignment;

    private ListNotificationDTO notification;

    private RefVAReportDTO vaReport;

    @JsonFormat(pattern="dd MMM, yyyy hh:mm a")
    private LocalDateTime createdAt;

    @JsonFormat(pattern="dd MMM, yyyy hh:mm a")
    private LocalDateTime updatedAt;
}
