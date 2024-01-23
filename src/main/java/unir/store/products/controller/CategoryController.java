package unir.store.products.controller;

import org.springframework.web.bind.annotation.RestController;

import unir.store.products.entity.Category;
import unir.store.products.service.CategoryService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/category" )
public class CategoryController {
    
    @Autowired
    private final CategoryService categoryService;

   
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    } 

    @GetMapping
    public List<Category> getCategories() {
        return this.categoryService.getAllCategories();
    }

    @PostMapping
    public Category createCategory(@RequestBody Category entity) {
        return this.categoryService.createCategory(entity);
    }
    
    


    
}
