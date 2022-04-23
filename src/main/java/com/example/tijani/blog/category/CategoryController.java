package com.example.tijani.blog.category;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoryController {

  private final CategoryService categoryService;
  private final ModelMapper modelMapper;

  @PostMapping("/admin/add")
  public ResponseEntity<CategoryDTO> saveCategory(@RequestBody @Valid Category category) {
    Category newCategory = categoryService.saveCategory(category);
    CategoryDTO categoryDTO = modelMapper.map(newCategory, CategoryDTO.class);
    return new ResponseEntity<CategoryDTO>(categoryDTO, HttpStatus.CREATED);
  }

  @GetMapping("/all")
  public ResponseEntity<List<CategoryDTO>> getCategories() {
    List<CategoryDTO> categoryDTOList = categoryService.getAllCategories().stream()
        .map(category -> modelMapper.map(category, CategoryDTO.class)).collect(
            Collectors.toList());
    return new ResponseEntity<List<CategoryDTO>>(categoryDTOList, HttpStatus.OK);
  }

  @GetMapping("/{slug}")
  public ResponseEntity<CategoryDTO> getCategory(@PathVariable String slug) {
    Category category = categoryService.getCategoryBySlug(slug);
    CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);
    return new ResponseEntity<CategoryDTO>(categoryDTO, HttpStatus.OK);
  }

  @DeleteMapping("/admin/delete/{categoryId}")
  public ResponseEntity<HttpStatus> deleteCategory(@PathVariable Integer categoryId) {
    categoryService.deleteCategory(categoryId);
    return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
  }

  @PutMapping("/admin/update/{categoryId}")
  public ResponseEntity<CategoryDTO> updateCategory(@RequestBody @Valid Category updatedCategory,@PathVariable("categoryId") Integer categoryId){
    updatedCategory.setId(categoryId);
    Category category = categoryService.updateCategory(updatedCategory);
    CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);
    return new ResponseEntity<CategoryDTO>(categoryDTO, HttpStatus.OK);
  }


}
