package com.example.tijani.blog.comment;

import com.example.tijani.blog.exception.ResourceNotFoundException;
import com.example.tijani.blog.post.Post;
import com.example.tijani.blog.post.PostRepository;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CommentService {
  private final CommentRepository commentRepository;
  private final PostRepository postRepository;

  public List<Comment> getCommentsForPost(Long postId){
    if(!postRepository.existsById(postId)){
      throw new ResourceNotFoundException("Post with id "+ postId +" not found.");
    }
    return commentRepository.findByPostId(postId);
  }

  public Comment saveComment(Comment comment, Long postId){
    Optional<Post> post = postRepository.findById(postId);
    if(post.isEmpty()){
      System.out.println("Invalid post id");
    }
    comment.setPost(post.get());
    return commentRepository.save(comment);
  }




}
