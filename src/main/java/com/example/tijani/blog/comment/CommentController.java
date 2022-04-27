package com.example.tijani.blog.comment;


import java.util.List;
import java.util.stream.Collectors;
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
  public ResponseEntity<List<CommentListDTO>> getPostComments(@PathVariable("postId") Long postId) {
    List<CommentListDTO> commentDTOList = commentService.getCommentsForPost(postId).stream()
        .map(comment -> modelMapper.map(comment, CommentListDTO.class)).collect(
            Collectors.toList());
    return new ResponseEntity<List<CommentListDTO>>(commentDTOList,
        HttpStatus.OK);
  }

  @PostMapping("/{postId}")
  public ResponseEntity<CommentDTO> saveComment(@RequestBody @Valid CommentRequest commentRequest,
      @PathVariable("postId") Long postId) {
    Comment newComment = commentService.saveComment(commentRequest, postId);
    CommentDTO commentDTO = modelMapper.map(newComment, CommentDTO.class);
    return new ResponseEntity<CommentDTO>(commentDTO,
        HttpStatus.CREATED);
  }
}
