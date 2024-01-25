package unir.store.products.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        @GetMapping(path="/id/{idCategory}")
        public Optional<Category> getCategoryById(@PathVariable String idCategory) throws GenericException {
                return this.categoryService.getCategoryById(idCategory);
        }

        @GetMapping(path="/name/{nameCategory}")
        public List<Category> getCategoryByName(@PathVariable String nameCategory) throws GenericException {
                return this.categoryService.getCategoryByName(nameCategory);
        }
        @DeleteMapping("/id/{idCategory}")
        public ResponseEntity<Void> removeCategoryById(@PathVariable String idCategory) throws GenericException {
                Boolean removed = this.categoryService.removeCategoryById(idCategory);
                if (Boolean.TRUE.equals(removed)) {
                        return ResponseEntity.ok().build();
                } else {
                        return ResponseEntity.notFound().build();
                }
        }

        @DeleteMapping("/name/{nameCategory}")
        public ResponseEntity<Void> removeCategoryByName(@PathVariable String nameCategory) throws GenericException {
                Boolean removed = this.categoryService.removeCategoryByName(nameCategory);
                if (Boolean.TRUE.equals(removed)) {
                        return ResponseEntity.ok().build();
                } else {
                        return ResponseEntity.notFound().build();
                }
        }




}