package com.example.tijani.blog.tag;


import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tags/admin")
@AllArgsConstructor
public class TagController {

  private final TagService tagService;
  private final ModelMapper modelMapper;


  @PostMapping
  public ResponseEntity<TagDTO> saveTag(@RequestBody @Valid Tag tag) {
    Tag newTag = tagService.saveTag(tag);
    TagDTO tagDTO = modelMapper.map(newTag, TagDTO.class);
    return new ResponseEntity<TagDTO>(tagDTO, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<TagDTO>> getTags() {
    List<TagDTO> tagDTO = tagService.getAllTags().stream()
        .map(tag -> modelMapper.map(tag, TagDTO.class)).collect(
            Collectors.toList());
    return new ResponseEntity<List<TagDTO>>(tagDTO, HttpStatus.OK);
  }

  @DeleteMapping("/{tagId}")
  public ResponseEntity<HttpStatus> deleteTag(@PathVariable Integer tagId) {
    tagService.deleteTag(tagId);
    return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
  }

  @GetMapping("/{tagId}")
  public ResponseEntity<TagDTO> getTag(@PathVariable Integer tagId) {
    Tag tag = tagService.getTag(tagId);
    TagDTO tagDTO = modelMapper.map(tag, TagDTO.class);
    return new ResponseEntity<TagDTO>(tagDTO, HttpStatus.OK);
  }
}
