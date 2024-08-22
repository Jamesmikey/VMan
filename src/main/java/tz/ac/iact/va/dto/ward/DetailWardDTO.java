package tz.ac.iact.va.dto.ward;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tz.ac.iact.va.dto.district.DetailDistrictDTO;
import tz.ac.iact.va.dto.district.RefDistrictDTO;
import tz.ac.iact.va.dto.region.RefRegionDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailWardDTO {

    private String id;

    private String name;

    private DetailDistrictDTO district;

}
