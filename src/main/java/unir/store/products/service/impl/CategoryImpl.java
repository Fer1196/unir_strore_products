package unir.store.products.service.impl;
import java.util.List;

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

}