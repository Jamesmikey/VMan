package tz.ac.iact.va.dto.region;

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
public class CreateRegionDTO {

    @NotEmpty(message = "Must specify the name")
    private String name;

}
