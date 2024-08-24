package tz.ac.iact.va.dto.notification;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tz.ac.iact.va.dto.ward.RefWardDTO;
import tz.ac.iact.va.enums.Sex;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListNotificationDTO {

    private String id;

    private String name;

    private RefWardDTO ward;

    private Sex sex;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dob;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dod;

}
