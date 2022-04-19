package com.example.tijani.blog.post;


import com.example.tijani.blog.category.Category;
import com.example.tijani.blog.comment.Comment;
import com.example.tijani.blog.tag.Tag;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Post {

  @Id
  @SequenceGenerator(
      name = "post_sequence",
      sequenceName = "post_sequence",
      allocationSize = 1
  )
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_sequence")
  private Long id;

  @Size(min = 5, max = 255)
  private String title;

  @Column(unique = true, nullable = false)
  private String slug;

  @Column(columnDefinition = "TEXT")
  @NotBlank(message = "Content is required.")
  private String content;


  @Column(name = "created_at", updatable = false, nullable = false)
  @Temporal(value = TemporalType.DATE)
  @CreationTimestamp
  private Date createdAt;

  @Temporal(value = TemporalType.DATE)
  @Column(name = "updated_at")
  @UpdateTimestamp
  private Date updatedAt;


  @OneToMany(mappedBy = "post")
  private List<Comment> comments;

  @ManyToOne
  @JoinColumn(name = "category_id", nullable = false)
  private Category category;

  @ManyToMany
  @JoinTable(name = "post_tag",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
  )
  private List<Tag> tags;

}
