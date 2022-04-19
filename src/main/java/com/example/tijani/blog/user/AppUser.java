package com.example.tijani.blog.user;

import com.example.tijani.blog.post.Post;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AppUser {

  @SequenceGenerator(
      name = "user_sequence",
      sequenceName = "user_sequence",
      allocationSize = 1
  )
  @Id
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator = "user_sequence"
  )
  private Long id;
  @Size(min = 3, max = 25)
  private String firstName;
  @Size(min = 3, max = 25)
  private String lastName;

  @Email
  private String email;
  @Size(min = 8, max = 25)
  private String password;

//  @OneToMany(mappedBy = "appUser")
//  private List<Post> posts;

  public AppUser(String firstName, String lastName, String email, String password) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
  }
}
