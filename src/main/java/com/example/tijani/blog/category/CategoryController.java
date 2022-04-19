package com.example.tijani.blog.category;

import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
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
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoryController {

  private final CategoryService categoryService;

  @PostMapping
  public ResponseEntity<Category> saveCategory(@RequestBody @Valid Category category){
    return new ResponseEntity<Category>(categoryService.saveCategory(category), HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<Category>> getCategories(){
    return new ResponseEntity<List<Category>>(categoryService.getAllCategories(), HttpStatus.OK);
  }

  @GetMapping("/{slug}")
  public ResponseEntity<Category> getCategory(@PathVariable String slug){
    return new ResponseEntity<>(categoryService.getCategoryBySlug(slug), HttpStatus.OK);
  }

  @DeleteMapping("/{categoryId}")
  public ResponseEntity<HttpStatus> deleteCategory(@PathVariable Integer categoryId){
    categoryService.deleteCategory(categoryId);
    return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
  }




}
