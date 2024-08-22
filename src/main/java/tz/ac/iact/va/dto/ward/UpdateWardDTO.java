package tz.ac.iact.va.dto.ward;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tz.ac.iact.va.dto.district.DetailDistrictDTO;
import tz.ac.iact.va.dto.district.RefDistrictDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateWardDTO {

    @NotEmpty(message = "Must specify the name")
    private String name;

    @NotNull(message = "Must select district")
    private DetailDistrictDTO district;

}
