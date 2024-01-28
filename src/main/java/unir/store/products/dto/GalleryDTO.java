package unir.store.products.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GalleryDTO {
    private Long id;
    private Long idProduct;
    private String detalle;
    private String es_principal;
    private String url;
}
