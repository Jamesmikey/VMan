package tz.ac.iact.va.dto.notification;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tz.ac.iact.va.enums.Sex;
import tz.ac.iact.va.model.Ward;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateNotificationDTO {

    @NotEmpty(message = "Must specify the name")
    private String name;

    @NotNull(message = "Must select the ward")
    private Ward ward;

    @NotNull(message = "Must provide the sex")
    private Sex sex;

    @NotNull(message = "Must provide the date of birth")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dob;

    @NotNull(message = "Must provide the date of death")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dod;

}
