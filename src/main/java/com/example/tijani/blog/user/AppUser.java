package com.example.tijani.blog.user;

import java.util.Collection;
import java.util.Collections;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AppUser implements UserDetails {

  @SequenceGenerator(
      name = "user_sequence",
      sequenceName = "user_sequence",
      allocationSize = 1
  )
  @Id
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator = "user_sequence"
  )
  private Long id;

  @Size(min = 3, max = 25)
  @Column(unique = true, nullable = false)
  private String username;

  @Size(min = 3, max = 25)
  private String firstName;
  @Size(min = 3, max = 25)
  private String lastName;

  @Email
  private String email;

  @Size(min = 8, max = 65)
  private String password;

  @Enumerated(EnumType.STRING)
  private UserRole userRole;
  private Boolean locked = false;
  private Boolean enabled = false;

//  @OneToMany(mappedBy = "appUser")
//  private List<Post> posts;

  public AppUser(String username, String firstName, String lastName, String email, String password,
      UserRole userRole) {
    this.username = username;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.userRole = userRole;

  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole.name());
    return Collections.singletonList(authority);
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return !locked;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return enabled;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public String getPassword() {
    return password;
  }
}
