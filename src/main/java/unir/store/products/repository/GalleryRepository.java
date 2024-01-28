package unir.store.products.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import unir.store.products.entity.Gallery;
import unir.store.products.entity.Product;

@Repository
public interface GalleryRepository extends JpaRepository<Gallery, Long>{
    List<Gallery> findByProduct(Product product);
    
    @Query (
        value = "SELECT g FROM GALLERY  g WHERE g.id_product =?1 ", 
        nativeQuery = true
    )
    public Gallery getAllProductById(Long idProduct);

}

