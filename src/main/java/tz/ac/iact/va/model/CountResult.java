package tz.ac.iact.va.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
public class CountResult {

    @Field("count")
    private long count;

}
