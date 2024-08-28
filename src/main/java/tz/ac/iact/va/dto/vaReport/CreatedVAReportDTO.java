package tz.ac.iact.va.dto.vaReport;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tz.ac.iact.va.dto.interview.RefInterviewDTO;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatedVAReportDTO {

    private RefInterviewDTO interview;

}
