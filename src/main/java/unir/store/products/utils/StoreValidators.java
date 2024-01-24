package unir.store.products.utils;

import org.springframework.stereotype.Component;

import unir.store.products.dto.CategoryDTO;
import unir.store.products.exception.GenericException;

@Component
public class StoreValidators {

        public void validateCategory(CategoryDTO category) throws GenericException {

                if(category == null)
                        throw new GenericException("Required Parameters", null);
                else if (category.getName().isBlank() || category.getName() == null)
                        throw new GenericException("Required product name", null);
        }

}
