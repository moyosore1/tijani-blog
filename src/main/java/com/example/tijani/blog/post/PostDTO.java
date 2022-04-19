package com.example.tijani.blog.post;

import com.example.tijani.blog.tag.TagDTO;
import java.util.Date;
import lombok.Data;

@Data
public class PostDTO {

  private Long id;
  private String title;
  private String slug;
  private String content;
  private TagDTO tags;
  private Date createdAt;
  private Date updatedAt;
}
