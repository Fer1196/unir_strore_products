package unir.store.products.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name="category")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Category {
    
    @Id
    @SequenceGenerator(
        name = "SEQ_CATEGORY", 
        sequenceName = "SEQ_CATEGORY",
        allocationSize = 1, 
        initialValue = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "SEQ_CATEGORY"
    )
    private Long idCategory;

    @Column(name="NAME_CATEGORY", nullable = false, unique = true)
    private String name;
    


}
