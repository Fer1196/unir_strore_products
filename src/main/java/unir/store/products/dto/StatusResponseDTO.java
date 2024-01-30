package unir.store.products.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StatusResponseDTO {
    private final String status;
    private final String message;
}
