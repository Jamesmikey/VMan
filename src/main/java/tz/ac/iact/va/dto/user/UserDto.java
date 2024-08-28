package tz.ac.iact.va.dto.user;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class UserDto {

  private String id;


  private String firstName;


  private String secondName;


  private String lastName;


  private String phoneNumber;

  private LocalDateTime createdAt;

  private String email;


  private String username;

  private String country;

  private boolean emailVerified;

  private String verificationCode;

  private Set<String> roles=new HashSet<>();

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String password;

  public String getFullName(){
    String middleName=this.secondName==null?"":this.secondName;
    return this.firstName+" "+middleName+" "+this.lastName;
  }

}