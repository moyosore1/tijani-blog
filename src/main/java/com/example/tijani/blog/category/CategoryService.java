package com.example.tijani.blog.category;

import com.example.tijani.blog.exception.ApiRequestException;
import com.example.tijani.blog.exception.ResourceNotFoundException;
import com.github.slugify.Slugify;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryService {
  private final CategoryRepository categoryRepository;
  private final Slugify slug;

  public boolean checkIfSlugIsAvailable(String categorySlug){

    if(categoryRepository.findBySlug(categorySlug).isPresent()){
      throw new ApiRequestException("Category with slug "+ categorySlug+ " gotten from name provided, already exists.");
    }
    return true;
  }
  public Category saveCategory(Category category){
    String categorySlug = slug.slugify(category.getName());
    if(checkIfSlugIsAvailable(categorySlug)){
      category.setSlug(categorySlug);
    }

    return categoryRepository.save(category);
  }

  public List<Category> getAllCategories(){
    Sort sort = Sort.by(Direction.ASC, "id");
    return categoryRepository.findAll(sort);
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

  public Category findCategoryById(Integer categoryId){
    Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category with id "+ categoryId+ " does not exist"));
    return category;
  }

  public boolean checkIfCategoryExists(Integer categoryId){
    if(categoryRepository.existsById(categoryId)){
      return true;
    }
    return false;
  }

  public Category updateCategory(Category updatedCategory){
    Category category = categoryRepository.findById(updatedCategory.getId()).orElseThrow(() -> new ResourceNotFoundException("Category with id "+ updatedCategory.getId()+ " does not exist"));
    if(updatedCategory.getName() != null && !updatedCategory.getName().isEmpty()){
      category.setName(updatedCategory.getName());
      String categorySlug = slug.slugify(updatedCategory.getName());
      if(checkIfSlugIsAvailable(categorySlug)){
        category.setSlug(categorySlug);
      }
    }
    return categoryRepository.save(category);
  }
}
