package tz.ac.iact.va.dto.ward;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tz.ac.iact.va.dto.district.RefDistrictDTO;
import tz.ac.iact.va.dto.region.RefRegionDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateWardDTO {

    @NotEmpty(message = "Must specify the name")
    private String name;

    @NotNull(message = "Must select district")
    private RefDistrictDTO district;

}
