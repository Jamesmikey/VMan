package tz.ac.iact.va.dto.user;

import lombok.*;

/**
 * @author James S. Michael
 * @created 20-09-2022 01:53:07
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class MessageResponse {

    @NonNull
    private boolean success;

    @NonNull
    private String message;

    private Object meta;
}
