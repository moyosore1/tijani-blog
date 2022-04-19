package com.example.tijani.blog.comment;

import com.example.tijani.blog.exception.ResourceNotFoundException;
import com.example.tijani.blog.post.Post;
import com.example.tijani.blog.post.PostRepository;
import com.example.tijani.blog.post.PostService;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CommentService {
  private final CommentRepository commentRepository;
  private final PostService postService;

  public List<Comment> getCommentsForPost(Long postId){
    if(!postService.checkIfPostIdExists(postId)){
      throw new ResourceNotFoundException("Post with id " + postId+" not found");
    }else {
      return commentRepository.findByPostId(postId);
    }
  }

  public Comment saveComment(Comment comment, Long postId){
    Post post = postService.findPostById(postId);
    comment.setPost(post);
    return commentRepository.save(comment);
  }




}
