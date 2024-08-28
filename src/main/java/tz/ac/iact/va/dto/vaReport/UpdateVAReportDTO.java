package tz.ac.iact.va.dto.vaReport;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tz.ac.iact.va.dto.interview.RefInterviewDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateVAReportDTO {

    Object values;
}
