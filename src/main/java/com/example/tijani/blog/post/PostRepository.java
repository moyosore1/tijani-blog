package com.example.tijani.blog.post;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

  Optional<Post> findBySlug(String slug);
  List<Post> findByCategoryId(Integer id, Pageable pageable);

}
