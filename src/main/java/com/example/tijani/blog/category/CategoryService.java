package com.example.tijani.blog.category;

import com.example.tijani.blog.exception.ApiRequestException;
import com.example.tijani.blog.exception.ResourceNotFoundException;
import com.github.slugify.Slugify;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryService {
  private final CategoryRepository categoryRepository;
  private final Slugify slug;

  public Category saveCategory(Category category){
    String categorySlug = slug.slugify(category.getName());
    if(categoryRepository.findBySlug(categorySlug).isPresent()){
      throw new ApiRequestException("Category with slug "+ categorySlug+ " gotten from name provided, already exists.");
    }
    category.setSlug(categorySlug);
    return categoryRepository.save(category);
  }

  public List<Category> getAllCategories(){
    return categoryRepository.findAll();
  }

  public Category getCategoryBySlug(String slug){
    Category category = categoryRepository.findBySlug(slug).orElseThrow(()-> new ResourceNotFoundException("Category with slug "+slug+ " was not found"));
    return category;
  }

  public void deleteCategory(Integer categoryId){
    if(!categoryRepository.existsById(categoryId)){
      throw new ResourceNotFoundException("Category with id "+ categoryId+ " does not exist");
    }
    categoryRepository.deleteById(categoryId);
  }


}
