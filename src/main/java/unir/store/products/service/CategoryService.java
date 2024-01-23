package unir.store.products.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import unir.store.products.entity.Category;
import unir.store.products.repository.CategoryRepository;

@Service
public class CategoryService {
    
    @Autowired
    private final CategoryRepository categoryRepository;

  
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    } 


    public List<Category> getAllCategories(){
        return this.categoryRepository.findAll(); 
    }

    public Category createCategory(Category category){
        return this.categoryRepository.save(category);
    }
 

}
