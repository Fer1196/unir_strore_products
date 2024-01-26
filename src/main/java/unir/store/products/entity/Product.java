package unir.store.products.entity;


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
@Table (name="product")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Product {
    @Id
    @SequenceGenerator(
        name = "SEQ_PRODUCT", 
        sequenceName = "SEQ_PRODUCT",
        allocationSize = 1, 
        initialValue = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "SEQ_PRODUCT"
    )
    private Long idProduct;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_category")
    private Category category;

    @Column(name="TITLE_PRODUCT", nullable = false)
    private String title;

    @Column(name="DESCRIPTION_PRODUCT", nullable = false)
    private String description;

    @Column(name="PRICE_PRODUCT", nullable = false)
    private double price;

    @Column(name="DISCOUNT_PERCENTAGE_PRODUCT", nullable = false)
    private double discountPercentage;
    
    @Column(name="RATING_PRODUCT", nullable = false)
    private double rating;
    
    @Column(name="STOCK_PRODUCT", nullable = false)
    private int stock;

    @Column(name="BRAND_PRODUCT", nullable = false)
    private String brand;

    @Column(name="THUMBNAIL_PRODUCT", nullable = false)
    private String thumbnail;

}
