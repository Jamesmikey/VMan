package tz.ac.iact.va.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
@TypeAlias("user")
public class User implements UserDetails {

  @Id
  private String id;

  private String firstName;

  private String secondName;

  private String lastName;

  private String phoneNumber;

  private String region;

  private String district;

  private String sex;

  private String email;

  private String password;

  @DocumentReference
  private Set<Role> roles=new HashSet<>();

  private boolean enabled;

  @CreatedDate
  private LocalDateTime createdAt;

  @LastModifiedDate
  private LocalDateTime updatedAt;

  @Version
  private Integer version;

  public String getFullName(){
    return this.firstName+" "+(this.secondName!=null?this.secondName:"")+" "+this.lastName;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return roles.stream()
            .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
            .collect(Collectors.toSet());
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}