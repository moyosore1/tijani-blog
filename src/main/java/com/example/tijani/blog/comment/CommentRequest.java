package com.example.tijani.blog.comment;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentRequest {

  @Size(min = 3, max = 25, message = "Name should have between 3 and 25 characters.")
  private String name = "Anonymous User";

  @Column(columnDefinition = "TEXT")
  @NotBlank(message = "Content is required.")
  private String content;



}
