package com.example.tijani.blog.user;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppUserService {
  private final AppUserRepository userRepository;


  public String signupUser(AppUser user) {
    boolean userExists = userRepository.findByEmail(user.getEmail())
        .isPresent();

    if (userExists) {
      System.out.println("User exists");
    }

    // encode password set by client and set it to user's password
//    String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());


    userRepository.save(user);

    // Create confirmation token

    return "Success!";
  }


}
