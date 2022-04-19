package com.example.tijani.blog.comment;

import com.example.tijani.blog.post.PostDTO;
import java.util.Date;
import lombok.Data;

@Data
public class CommentDTO {

  private Long id;
  private String name;
  private CommentPostDTO post;
  private Date createdAt;

}
