package com.example.tijani.blog.comment;


import java.util.Date;
import lombok.Data;

@Data
public class CommentDTO {

  private Long id;
  private String name;
  private String content;
  private CommentPostDTO post;
  private Date createdAt;

}
