package com.example.tijani.blog.user;


import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AppUserService implements UserDetailsService {
  private final AppUserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    Optional<AppUser> usersOptional = userRepository.findByUsername(username);

    usersOptional
        .orElseThrow(() -> new UsernameNotFoundException("Username not found!"));
    return usersOptional.get();
  }


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
