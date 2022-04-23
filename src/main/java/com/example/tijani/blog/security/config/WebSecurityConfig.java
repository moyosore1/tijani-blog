package com.example.tijani.blog.security.config;


import com.example.tijani.blog.jwt.JwtConfig;
import com.example.tijani.blog.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import com.example.tijani.blog.jwt.JwtVerifier;
import com.example.tijani.blog.user.AppUserService;

import com.example.tijani.blog.user.UserRole;
import javax.crypto.SecretKey;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@AllArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private final AppUserService userService;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final SecretKey secretKey;
  private final JwtConfig jwtConfig;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(daoAuthenticationProvider());
  }

  @Bean
  public DaoAuthenticationProvider daoAuthenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setPasswordEncoder(bCryptPasswordEncoder);
    provider.setUserDetailsService(userService);
    return provider;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .csrf().disable()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()
        .antMatchers( "/categories/*", "/comments/*", "/posts/*", "/posts", "/posts/*/*", "/admin/registration", "/admin/registration/*", "/login").permitAll()
        .and()
        .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(), jwtConfig, secretKey))
        .addFilterAfter(new JwtVerifier(secretKey, jwtConfig),JwtUsernameAndPasswordAuthenticationFilter.class)
//        .authorizeRequests()
//        .antMatchers("/", "index",  "/tijani/api/v1/categories").permitAll()
//        .antMatchers("/api/**").hasRole(UserRole.ADMIN.name())
        .authorizeRequests()
        .anyRequest()
        .authenticated();





  }
}
