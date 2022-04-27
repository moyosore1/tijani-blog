package com.example.tijani.blog.comment;

import java.util.Date;
import lombok.Data;

@Data
public class CommentListDTO {

  private Long id;
  private String name;
  private String content;
  private Date createdAt;

}
