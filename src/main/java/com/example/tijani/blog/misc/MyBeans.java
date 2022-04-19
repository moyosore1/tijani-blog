package com.example.tijani.blog.misc;

import com.github.slugify.Slugify;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBeans {

  @Bean
  public Slugify getSlugify(){
    return new Slugify();
  }

}
