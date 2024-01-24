package unir.store.products.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryDTO {

        private final Long id;
        private final String name;

}