package com.example.tijani.blog.post;

import com.example.tijani.blog.category.Category;
import com.example.tijani.blog.category.CategoryRepository;
import com.example.tijani.blog.exception.ResourceNotFoundException;
import com.github.slugify.Slugify;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostService {

  private final PostRepository postRepository;
  private final CategoryRepository categoryRepository;
  private final Slugify slug;


  public List<Post> getAllPosts(int pageNumber) {
    Pageable pages = PageRequest.of(pageNumber, 10, Direction.DESC, "createdAt");
//    Sort sort = Sort.by(Sort.Direction.ASC)
    return postRepository.findAll(pages).getContent();
  }

  public Post createNewPost(Post post) {
    String postSlug = slug.slugify(post.getTitle());
    if (postRepository.findBySlug(postSlug).isPresent()) {
      System.out.println("Post with specified slug exists");
    }

    post.setSlug(postSlug);
    Optional<Category> category = categoryRepository.findById(post.getCategory().getId());
    if (!category.isPresent()) {
      System.out.println("Wrong category specified!");
    }
    post.setCategory(category.get());
    return postRepository.save(post);
  }

  public Post updatePost(Post post) {
    return postRepository.save(post);
  }

  public void deletePost(Long id) {
    if (!postRepository.existsById(id)) {
      System.out.println("Return 404 here");
    }
    postRepository.deleteById(id);
  }

  public Post getPostBySlug(String slug) {
    Optional<Post> post = postRepository.findBySlug(slug);
    if (!post.isPresent()) {
      throw new ResourceNotFoundException("Post with slug "+slug+ " does not exist.");
    }
    return post.get();
  }

  public List<Post> getPostsInCategory(Integer categoryId, int pageNumber) {
    if (!categoryRepository.existsById(categoryId)) {
      System.out.println("Return 404 here");
    }
//    Pageable pages = PageRequest.of(pageNumber, 10, Direction.DESC, "createdAt");
    return postRepository.findByCategoryId(categoryId);
  }

  public boolean checkIfPostIdExists(Long postId) {
    if (!postRepository.existsById(postId)) {

      return false;
    }
    return true;
  }

  public Post findPostById(Long postId) {
    Post post = postRepository.findById(postId).orElseThrow(
        () -> new ResourceNotFoundException("Post with id " + postId + " does not exist"));
    return post;
  }


}
