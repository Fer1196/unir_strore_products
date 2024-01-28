package unir.store.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import unir.store.products.dto.ProductDTO;
import unir.store.products.entity.Product;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
    List<Product> findByTitle(String title);

    @Modifying
    @Transactional
    @Query (
        value = "update product set stock_product = ?2 where id_product =?1 ", 
        nativeQuery = true
    )
    public void updateStock(Long idProduct, int quantity);
}
