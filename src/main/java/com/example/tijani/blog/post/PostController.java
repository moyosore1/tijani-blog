package com.example.tijani.blog.post;

import java.util.List;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/posts")
public class PostController {

  private final PostService postService;

  @GetMapping
  public ResponseEntity<List<Post>> allPosts(@RequestParam int page){
    return new ResponseEntity<List<Post>>(postService.getAllPosts(page), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Post> createPost(@RequestBody @Valid Post post){
    return new ResponseEntity<Post>(postService.createNewPost(post), HttpStatus.CREATED);
  }

  @PutMapping("/{postId}")
  public ResponseEntity<Post> updatePost(@RequestBody @Valid Post post, @PathVariable Long postId){
    post.setId(postId);
    return new ResponseEntity<Post>(postService.updatePost(post), HttpStatus.OK);
  }

  @DeleteMapping("/{postId}")
  public ResponseEntity<HttpStatus> deletePost(@PathVariable Long postId){
    postService.deletePost(postId);
    return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
  }

  @GetMapping("/{slug}")
  public ResponseEntity<Post> getPost(@PathVariable String slug){
    return new ResponseEntity<>(postService.getPostBySlug(slug), HttpStatus.OK);
  }

  @GetMapping("/{categoryId}")
  public ResponseEntity<List<Post>> getPostsInCategory(@PathVariable Integer categoryId, @RequestParam int page){
    return new ResponseEntity<List<Post>>(postService.getPostsInCategory(categoryId, page), HttpStatus.OK);
  }





}
