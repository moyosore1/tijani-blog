package com.example.tijani.blog.category;

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
      System.out.println("slug exists");
    }
    category.setSlug(categorySlug);
    return categoryRepository.save(category);
  }

  public List<Category> getAllCategories(){
    return categoryRepository.findAll();
  }

  public Category getCategoryBySlug(String slug){
    Optional<Category> category = categoryRepository.findBySlug(slug);
    if(!category.isPresent()){
      System.out.println("404 NOT FOUND!");
    }
    return category.get();
  }

  public void deleteCategory(Integer categoryId){
    if(!categoryRepository.existsById(categoryId)){
      System.out.println("404");
    }
    categoryRepository.deleteById(categoryId);
  }


}
