package com.example.tijani.blog.tag;

import com.example.tijani.blog.exception.ResourceNotFoundException;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TagService {

  private final TagRepository tagRepository;

  public Tag saveTag(Tag tag) {
    return tagRepository.save(tag);
  }

  public List<Tag> getAllTags() {
    return tagRepository.findAll();
  }

  public void deleteTag(Integer id) {
    if (!tagRepository.existsById(id)) {
      throw new ResourceNotFoundException("Tag with id "+id+" does not exist.");
    }
    tagRepository.deleteById(id);
  }

  public Tag getTag(Integer id) {
    Optional<Tag> tag = tagRepository.findById(id);
    if (!tag.isPresent()) {
      throw new ResourceNotFoundException("Tag with id "+id+" does not exist.");
    }
    return tag.get();
  }


}
