package com.example.tijani.blog.comment;

import java.util.Date;
import lombok.Data;

@Data
public class CommentPostDTO {
  private Long id;
  private String title;
  private String slug;
  private Date createdAt;
  private Date updatedAt;
}
