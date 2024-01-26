package unir.store.products.dto;

import java.sql.Clob;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GalleryDTO {
    private final Long id;
    private final Long idProduct;
    private final String detalle;
    private final String es_principal;
    private final String url;
}
