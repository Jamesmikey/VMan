package tz.ac.iact.va.dto.ward;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tz.ac.iact.va.dto.region.RefRegionDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatedWardDTO {

    private String id;

    private String name;

    private RefRegionDTO district;
}
