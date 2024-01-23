package unir.store.products.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import unir.store.products.dto.CategoryDTO;
import unir.store.products.entity.Category;
import unir.store.products.repository.CategoryRepository;
import unir.store.products.service.ICategoryService;

@Service
public class CategoryImpl implements ICategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public List<Category> getAllCategories() {
		return this.categoryRepository.findAll();
	}

	public CategoryDTO createCategory(CategoryDTO category) {
		Category categ = new Category();
		categ.setName(category.getName());

		categ = this.categoryRepository.save(categ);
		category.setId(categ.getIdCategory());
		category.setName(categ.getName());
		return category;
	}

}
