package unir.store.products.service.impl;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import unir.store.products.dto.CategoryDTO;
import unir.store.products.entity.Category;
import unir.store.products.exception.GenericException;
import unir.store.products.repository.CategoryRepository;
import unir.store.products.service.ICategoryService;
import unir.store.products.utils.StoreValidators;

@Service
public class CategoryImpl implements ICategoryService {

        @Autowired
        private CategoryRepository categoryRepository;
        @Autowired
        StoreValidators storeValidators;

        public List<Category> getAllCategories() throws GenericException{
                return this.categoryRepository.findAll();
        }


        public CategoryDTO createCategory(CategoryDTO category) throws GenericException{
                try {

                        storeValidators.validateCategory(category);

                        Category categ = Category.builder()
                                        .name(category.getName())
                                        .build();

                        categ = this.categoryRepository.save(categ);

                        category = CategoryDTO.builder()
                        .name(categ.getName())
                        .id(categ.getIdCategory()).build();

                        return category;
                } catch (Exception e) {
                        throw new GenericException(e.getMessage(), null);
                }

        }

        public Optional<Category> getCategoryById(String idCategory) throws GenericException {
                try {
                        Long categoryId=Long.valueOf(idCategory);
                        return this.categoryRepository.findById(categoryId);
                } catch (Exception e) {
                        throw new GenericException(e.getMessage(), null);
                }
        }

        public List<Category> getCategoryByName(String name) throws GenericException {
                try {

                        return this.categoryRepository.findByName(name);
                } catch (Exception e) {
                        throw new GenericException(e.getMessage(), null);
                }
        }

        public Boolean removeCategoryById(String idCategory){

                Category category=this.categoryRepository.findById(Long.valueOf(idCategory)).orElse(null);
                if (category != null){
                        this.categoryRepository.delete(category);
                        return Boolean.TRUE;
                }else{
                        return Boolean.FALSE;
                }

        }
        public Boolean removeCategoryByName(String name){

                List<Category> categories=this.categoryRepository.findByName(name);
                if (!categories.isEmpty()){
                        Category categoryToRemove = categories.get(0);
                        this.categoryRepository.delete(categoryToRemove);
                        return Boolean.TRUE;
                }else{
                        return Boolean.FALSE;
                }

        }
}