package unir.store.products.service.Interface;

import java.util.List;

import org.springframework.stereotype.Service;

import unir.store.products.dto.GalleryDTO;
import unir.store.products.entity.Gallery;
import unir.store.products.exception.GenericException;

@Service
public interface IGalleryService {
    public List<Gallery> getAllGallery() throws GenericException;
    public GalleryDTO createGallery(GalleryDTO gallery) throws GenericException;
    public Gallery getGalleryById(String idGallery) throws GenericException;

}
