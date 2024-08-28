package tz.ac.iact.va.dto.assignment;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tz.ac.iact.va.dto.user.ListUserDTO;
import tz.ac.iact.va.dto.ward.ListWardDTO;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListAssignmentDTO {

    private ListWardDTO ward;

    private ListUserDTO user;

    @JsonFormat(pattern="dd MMM, yyyy hh:mm a")
    private LocalDateTime createdAt;

    @JsonFormat(pattern="dd MMM, yyyy hh:mm a")
    private LocalDateTime updatedAt;
}
