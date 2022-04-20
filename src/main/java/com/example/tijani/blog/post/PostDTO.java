package com.example.tijani.blog.post;

import com.example.tijani.blog.category.CategoryDTO;
import com.example.tijani.blog.tag.TagDTO;
import com.example.tijani.blog.user.UserDTO;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class PostDTO {

  private Long id;
  private String title;
  private String slug;
  private String content;
  private List<TagDTO> tags;
  private CategoryDTO category;
  private Date createdAt;
  private Date updatedAt;
  private UserDTO author;


}
