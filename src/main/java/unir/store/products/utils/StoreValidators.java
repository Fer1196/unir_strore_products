package unir.store.products.utils;

import java.util.List;

import org.springframework.stereotype.Component;

import unir.store.products.dto.CategoryDTO;
import unir.store.products.dto.GalleryDTO;
import unir.store.products.dto.ProductDTO;
import unir.store.products.entity.Product;
import unir.store.products.exception.GenericException;

@Component
public class StoreValidators {

        public void validateCategory(CategoryDTO category) throws GenericException {

                if(category == null)
                        throw new GenericException("Required Parameters", null);
                else if (category.getName().isBlank() || category.getName() == null)
                        throw new GenericException("Required product name", null);
        }

        public ProductDTO formatProductDTO (Product product, List<GalleryDTO> listGallery) throws GenericException {
                try {
                        if (product == null) {
                                throw new GenericException("Required Parameters", null);        
                        }
                        ProductDTO productDto = ProductDTO.builder()
                                        .idProduct(product.getIdProduct())
                                        .idCategory(product.getCategory().getIdCategory())
                                        .title(product.getTitle())
                                        .description(product.getDescription())
                                        .price(product.getPrice())
                                        .discountPercentage(product.getDiscountPercentage())
                                        .rating(product.getRating())
                                        .stock(product.getStock())
                                        .brand(product.getBrand())
                                        .thumbnail(product.getThumbnail())
                                        .gallery(listGallery)
                                        .build();
                        return productDto;
                } catch (Exception e) {
                        throw new GenericException(e.getMessage(), null);
                }
        }

}
