package com.example.tijani.blog.user;


import com.example.tijani.blog.exception.ApiRequestException;
import com.example.tijani.blog.registration.EmailValidator;
import com.example.tijani.blog.registration.token.ConfirmationToken;
import com.example.tijani.blog.registration.token.ConfirmationTokenService;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AppUserService implements UserDetailsService {
  private final AppUserRepository userRepository;
  private final static String USER_NOT_FOUND_MSG = "User with email %s not found";
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final ConfirmationTokenService confirmationTokenService;
  private final EmailValidator emailValidator;

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
    if(!emailValidator.test(user.getEmail())){
      throw new ApiRequestException(user.getEmail() + " is not valid.");
    }
    if (userExists) {
      throw new ApiRequestException("Email has already been taken.");
    }

    // encode password set by client and set it to user's password
    String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
    user.setPassword(encodedPassword);

    userRepository.save(user);



    // Create confirmation token
    String token = UUID.randomUUID().toString();
    ConfirmationToken confirmationToken = new ConfirmationToken(
        token,
        LocalDateTime.now(),
        LocalDateTime.now().plusMinutes(15),
        user
    );
    confirmationTokenService.saveConfirmationToken(confirmationToken);


    // send
    return token;
  }

  public AppUser currentUser(String username) throws UsernameNotFoundException {
    return userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, username)));
  }

  public int enableAppUser(String email) {
    return userRepository.enableAppUser(email);
  }

  public AppUser getUserById(Long userId){
    return userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("The user specified does not exist"));
  }





}
