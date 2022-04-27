package com.example.tijani.blog.comment;

import com.example.tijani.blog.post.Post;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;


@Entity
@NoArgsConstructor
@Getter
@Setter
public class Comment {

  @Id
  @SequenceGenerator(
      name = "comment_sequence",
      sequenceName = "comment_sequence",
      allocationSize = 1
  )
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_sequence")
  private Long id;

  @NotBlank
  @Size(min = 3, max = 25, message = "Name should have between 3 and 25 characters.")
  private String name;

  @Column(columnDefinition = "TEXT")
  @NotBlank(message = "Content is required.")
  private String content;


  @ManyToOne
  @JoinColumn(name = "post_id", nullable = false)
  private Post post;



  @Column(name = "created_at", updatable = false, nullable = false)
  @Temporal(value = TemporalType.DATE)
  @CreationTimestamp
  private Date createdAt;


  public Comment(String name, String content) {
    this.name = name;
    this.content = content;
  }
}

