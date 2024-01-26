package unir.store.products.entity;

import java.sql.Clob;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name="gallery")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Gallery {
    @Id
    @SequenceGenerator(
        name = "SEQ_GALLERY", 
        sequenceName = "SEQ_GALLERY",
        allocationSize = 1, 
        initialValue = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "SEQ_GALLERY"
    )
    private Long idGallery;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_product")
    private Product product;

    @Column(name="DETALLE_GALLERY", nullable = false)
    private String detalle;

    @Column(name="ES_PRINCIPAL_GALLERY", nullable = false)
    private String es_principal;

    @Column(name="URL_GALLERY", nullable = false)
    private String url;
}
