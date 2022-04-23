package com.example.tijani.blog.post;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class PostRequest {

  @Size(min = 5, max = 255)
  private String title;
  @NotBlank(message = "Content shall be required to create post.")
  private String content;

  private int category = 0;
}
