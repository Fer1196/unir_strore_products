package unir.store.products.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import unir.store.products.dto.CategoryDTO;
import unir.store.products.entity.Category;
import unir.store.products.exception.GenericException;
import unir.store.products.service.ICategoryService;
import unir.store.products.utils.GenericBasicResponse;

@RestController
@RequestMapping("/category")
public class CategoryController {

        @Autowired
        private ICategoryService categoryService;

        public CategoryController(ICategoryService categoryService) {
                this.categoryService = categoryService;
        }

        private GenericBasicResponse<CategoryDTO> response;

        @GetMapping
        public List<Category> getCategories() throws GenericException {
                return this.categoryService.getAllCategories();
        }

        @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        public GenericBasicResponse<CategoryDTO> createCategory(@RequestBody CategoryDTO request) throws GenericException {
                response = new GenericBasicResponse<>();
                response.setData(this.categoryService.createCategory(request));
                return response;
        }

}