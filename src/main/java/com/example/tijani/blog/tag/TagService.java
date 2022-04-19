package com.example.tijani.blog.tag;

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
      System.out.println("Error 404!");
    }
    tagRepository.deleteById(id);
  }

  public Tag getTag(Integer id) {
    Optional<Tag> tag = tagRepository.findById(id);
    if (!tag.isPresent()) {
      System.out.println("Error 404");
    }
    return tag.get();
  }


}
