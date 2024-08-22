package tz.ac.iact.va.dto.district;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tz.ac.iact.va.dto.region.RefRegionDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatedDistrictDTO {

    private String id;

    private String name;

    private RefRegionDTO region;
}
