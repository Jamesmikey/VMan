package tz.ac.iact.va.dto.region;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRegionDTO {

    @NotEmpty(message = "Must specify the name")
    private String name;

}
