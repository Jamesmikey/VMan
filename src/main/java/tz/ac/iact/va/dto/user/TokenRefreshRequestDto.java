package tz.ac.iact.va.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenRefreshRequestDto {
  @NotBlank(message = "Must provide token")
  private String refreshToken;
  
}