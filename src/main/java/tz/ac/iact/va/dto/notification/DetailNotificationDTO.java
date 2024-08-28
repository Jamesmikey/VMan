package tz.ac.iact.va.dto.notification;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tz.ac.iact.va.dto.interview.DetailInterviewDTO;
import tz.ac.iact.va.dto.interview.RefInterviewDTO;
import tz.ac.iact.va.dto.user.DetailUserDTO;
import tz.ac.iact.va.dto.ward.DetailWardDTO;
import tz.ac.iact.va.enums.Sex;
import tz.ac.iact.va.model.Interview;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailNotificationDTO {

    private String id;

    private String name;

    private DetailWardDTO ward;

    private Sex sex;

    private RefInterviewDTO interview;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dob;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dod;

    @JsonFormat(pattern="dd MMM, yyyy hh:mm a")
    private LocalDateTime createdAt;

    @JsonFormat(pattern="dd MMM, yyyy hh:mm a")
    private LocalDateTime updatedAt;

    private DetailUserDTO reportedBy;

}
