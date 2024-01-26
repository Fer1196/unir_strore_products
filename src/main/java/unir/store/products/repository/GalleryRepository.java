package unir.store.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import unir.store.products.entity.Gallery;

public interface GalleryRepository extends JpaRepository<Gallery, Long>{
}

