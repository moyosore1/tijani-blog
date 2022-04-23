package com.example.tijani.blog.post;

import com.example.tijani.blog.category.Category;
import com.example.tijani.blog.category.CategoryRepository;
import com.example.tijani.blog.category.CategoryService;
import com.example.tijani.blog.exception.ApiRequestException;
import com.example.tijani.blog.exception.ResourceNotFoundException;
import com.example.tijani.blog.user.AppUser;
import com.example.tijani.blog.user.AppUserService;
import com.github.slugify.Slugify;
import java.security.Principal;
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
  private final AppUserService userService;
  private final Slugify slug;
  private final CategoryService categoryService;


  public List<Post> getAllPosts(int pageNumber) {
    Pageable pages = PageRequest.of(pageNumber, 10, Direction.DESC, "createdAt");

    return postRepository.findAll(pages).getContent();
  }

  public Post createNewPost(PostRequest postRequest, Principal currentUser) {
    Post post = new Post(postRequest.getTitle(), postRequest.getContent());
    String postSlug = slug.slugify(post.getTitle());
    if (postRepository.findBySlug(postSlug).isPresent()) {
      throw new ApiRequestException("Post with slug already exists. Try using a different title.");
    }

    post.setSlug(postSlug);
    Category category = categoryService.findCategoryById(postRequest.getCategory());
    post.setCategory(category);
    AppUser user = userService.currentUser(currentUser.getName());
    post.setAuthor(user);
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
    if (!categoryService.checkIfCategoryExists(categoryId)) {
      throw new ResourceNotFoundException("Category with id "+categoryId+ " does not exist.");

    }
    Pageable pages = PageRequest.of(pageNumber, 10, Direction.DESC, "createdAt");
    return postRepository.findByCategoryId(categoryId, pages);
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

  public Post updatePost(PostRequest postRequest, Principal user, Long postId){
    Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post with id " + postId + " does not exist"));
    AppUser currentUser = userService.currentUser(user.getName());
    if(post.getAuthor() == currentUser){
      if(postRequest.getTitle() != null && !post.getTitle().isEmpty()){
        post.setTitle(postRequest.getTitle());
      }
      if(postRequest.getContent() != null && !post.getContent().isEmpty()){
        post.setContent(postRequest.getContent());
      }
      if(postRequest.getCategory() != 0){
        Category category = categoryService.findCategoryById(postRequest.getCategory());
        post.setCategory(category);
      }
      post = postRepository.save(post);

    }
    return post;
  }


}
