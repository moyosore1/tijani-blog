package com.example.tijani.blog.registration;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
  private final String username;
  private final String firstName;
  private final String lastName;
  private final String email;
  private final String password;

}
