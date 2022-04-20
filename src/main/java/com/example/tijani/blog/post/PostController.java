package com.example.tijani.blog.post;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
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
  private final ModelMapper modelMapper;

  @GetMapping("/all")
  public ResponseEntity<List<PostDTO>> allPosts(@RequestParam int page) {
    List<PostDTO> postDTOList = postService.getAllPosts(page).stream()
        .map(post -> modelMapper.map(post, PostDTO.class)).collect(
            Collectors.toList());

    return new ResponseEntity<List<PostDTO>>(postDTOList, HttpStatus.OK);
  }

  @PostMapping("/admin/create")
  public ResponseEntity<PostDTO> createPost(@RequestBody @Valid PostRequest postRequest, Principal currentUser) {
    Post newPost = postService.createNewPost(postRequest, currentUser);
    PostDTO postDTO = modelMapper.map(newPost, PostDTO.class);
    return new ResponseEntity<PostDTO>(postDTO, HttpStatus.CREATED);
  }

  @PutMapping("/admin/update/{postId}")
  public ResponseEntity<PostDTO> updatePost(@RequestBody @Valid Post post,
      @PathVariable Long postId, Principal currentUser) {
    post.setId(postId);
    Post updatedPost = postService.updatePost(post, currentUser);
    PostDTO postDTO = modelMapper.map(updatedPost, PostDTO.class);
    return new ResponseEntity<PostDTO>(postDTO, HttpStatus.OK);
  }

  @DeleteMapping("/admin/delete/{postId}")
  public ResponseEntity<HttpStatus> deletePost(@PathVariable Long postId) {
    postService.deletePost(postId);
    return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
  }

  @GetMapping("/{slug}")
  public ResponseEntity<PostDTO> getPost(@PathVariable String slug) {
    Post post = postService.getPostBySlug(slug);
    PostDTO postDTO = modelMapper.map(post, PostDTO.class);
    return new ResponseEntity<PostDTO>(postDTO, HttpStatus.OK);
  }

  @GetMapping("/{categoryId}")
  public ResponseEntity<List<PostDTO>> getPostsInCategory(@PathVariable Integer categoryId,
      @RequestParam int page) {
    List<PostDTO> postDTOList = postService.getPostsInCategory(categoryId, page).stream()
        .map(post -> modelMapper.map(post, PostDTO.class)).collect(
            Collectors.toList());
    return new ResponseEntity<List<PostDTO>>(postDTOList, HttpStatus.OK);
  }

}
