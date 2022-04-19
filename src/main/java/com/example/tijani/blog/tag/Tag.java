package com.example.tijani.blog.tag;


import com.example.tijani.blog.post.Post;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Tag {

  @Id
  @SequenceGenerator(
      name = "post_sequence",
      sequenceName = "post_sequence",
      allocationSize = 1
  )
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_sequence")
  private Integer id;

  @NotBlank
  @Size(min = 3, max = 20)
  private String name;

  @ManyToMany
  private List<Post> posts;
}
