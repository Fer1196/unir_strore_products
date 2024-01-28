package unir.store.products.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import unir.store.products.dto.CategoryDTO;
import unir.store.products.entity.Category;
import unir.store.products.exception.GenericException;

@Service
public interface ICategoryService {

        public List<Category> getAllCategories()  throws GenericException;
        public CategoryDTO createCategory(CategoryDTO category)  throws GenericException;
        public Optional<Category> getCategoryById(String idCategory) throws GenericException;
        public List<Category> getCategoryByName(String name) throws GenericException;
        public Boolean removeCategoryById(String idCategory) throws GenericException;
        public Boolean removeCategoryByName(String name) throws GenericException;
}