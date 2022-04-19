package com.example.tijani.blog.user;


import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/user")
public class AppUserController {
  private final AppUserService userService;

  @PostMapping(path = "register")
  public String registerUser(@RequestBody @Validated AppUser user){
    return userService.signupUser(user);
  }
}
