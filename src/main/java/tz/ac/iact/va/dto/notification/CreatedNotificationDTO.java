package tz.ac.iact.va.dto.notification;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tz.ac.iact.va.dto.user.DetailUserDTO;
import tz.ac.iact.va.dto.ward.RefWardDTO;
import tz.ac.iact.va.enums.Sex;
import tz.ac.iact.va.model.User;
import tz.ac.iact.va.model.Ward;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatedNotificationDTO {

    private String id;

    private String name;

    private RefWardDTO ward;

    private Sex sex;

    @JsonFormat(pattern="dd MMM, yyyy hh:mm a")
    private LocalDateTime createdAt;

    @JsonFormat(pattern="dd MMM, yyyy hh:mm a")
    private LocalDateTime updatedAt;

    private DetailUserDTO reportedBy;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dob;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dod;

}
