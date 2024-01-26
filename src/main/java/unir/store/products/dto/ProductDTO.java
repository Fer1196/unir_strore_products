package unir.store.products.dto;

import java.util.Locale.Category;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class ProductDTO {
    private final Long idProduct;
    private final Category category;
    private final String title;
    private final String description;
    private final double price;
    private final double discountPercentage;
    private final double rating;
    private final int stock;
    private final String brand;
    private final String thumbnail;
}
