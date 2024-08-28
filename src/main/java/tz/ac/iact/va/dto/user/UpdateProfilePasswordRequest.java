package tz.ac.iact.va.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class UpdateProfilePasswordRequest {
    @NotBlank(message = "Old Password is mandatory")
    private String oldPassword;

    @NotBlank(message = "New Password is mandatory")
    private String newPassword;

}