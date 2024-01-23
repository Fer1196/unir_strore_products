package unir.store.products.service;

import java.util.List;

import org.springframework.stereotype.Service;

import unir.store.products.dto.CategoryDTO;
import unir.store.products.entity.Category;

@Service
public interface ICategoryService {

	public List<Category> getAllCategories();

	public CategoryDTO createCategory(CategoryDTO category);
}
