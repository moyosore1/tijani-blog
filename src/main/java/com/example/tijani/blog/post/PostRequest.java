package com.example.tijani.blog.post;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class PostRequest {
  private String title;
  private String content;
  private int category;
}
