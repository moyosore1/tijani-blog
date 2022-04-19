package com.example.tijani.blog.category;


import com.example.tijani.blog.post.Post;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Category {

  @Id
  @SequenceGenerator(
      name = "comment_sequence",
      sequenceName = "comment_sequence",
      allocationSize = 1
  )
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_sequence")
  private Integer id;

  @NotBlank
  @Size(min = 2, max = 30, message = "Category must be assigned a name.")
  private String name;

  @Column(nullable = false, unique = true)
  private String slug;

  @OneToMany(mappedBy = "category")
  private List<Post> posts;


}
