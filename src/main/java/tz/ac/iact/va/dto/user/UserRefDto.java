package tz.ac.iact.va.dto.user;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRefDto {
  @NotNull(message = "Must select user")
  private String id;

  private String fullName;

}