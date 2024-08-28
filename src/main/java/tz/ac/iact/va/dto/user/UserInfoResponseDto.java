package tz.ac.iact.va.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author James S. Michael
 * @created 20-09-2022 01:53:07
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoResponseDto {
    private String token;
    private String tokenType;
    private RefreshTokenDto refreshToken;
    private UserDto user;
}
