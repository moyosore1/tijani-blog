//package com.example.tijani.blog.user;
//
//
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@AllArgsConstructor
//@Service
//public class AppUserService {
//  private final AppUserRepository userRepository;
//
//
//  public String signupUser(AppUser user) {
//    boolean userExists = userRepository.findByEmail(user.getEmail())
//        .isPresent();
//
//    if (userExists) {
//      System.out.println("User exists");
//    }
//
//    // encode password set by client and set it to user's password
////    String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
//
//
//    userRepository.save(user);
//
//    // Create confirmation token
//
//    return "Success!";
//  }
//
//
//}
