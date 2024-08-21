package tz.ac.iact.va.dto.notification;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tz.ac.iact.va.model.Ward;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatedNotificationDTO {

    private String id;

    private String name;

    private Ward ward;

    private String sex;

    private LocalDate dob;

    private LocalDate dod;

}
