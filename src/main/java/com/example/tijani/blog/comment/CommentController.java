package com.example.tijani.blog.comment;


import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
@AllArgsConstructor
public class CommentController {

  private final CommentService commentService;
  private final ModelMapper modelMapper;

  @GetMapping("/{postId}")
  public ResponseEntity<List<CommentDTO>> getPostComments(@PathVariable("postId") Long postId) {
    List<CommentDTO> commentDTOList = commentService.getCommentsForPost(postId)
    return new ResponseEntity<List<Comment>>(,
        HttpStatus.OK);
  }

  @PostMapping("/{postId}")
  public ResponseEntity<Comment> saveComment(@RequestBody @Valid Comment comment,
      @PathVariable("postId") Long postId) {
    return new ResponseEntity<Comment>(commentService.saveComment(comment, postId),
        HttpStatus.CREATED);
  }
}
