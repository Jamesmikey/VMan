package tz.ac.iact.va.dto.ward;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tz.ac.iact.va.dto.district.DetailDistrictDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListWardDTO {

    private String id;

    private String name;

    private DetailDistrictDTO district;
}
