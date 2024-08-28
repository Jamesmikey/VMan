package tz.ac.iact.va.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefreshTokenDto {

  private String token;

  private Instant expiryDate;

}