package unir.store.products.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import unir.store.products.dto.GalleryDTO;
import unir.store.products.entity.Gallery;
import unir.store.products.entity.Product;
import unir.store.products.exception.GenericException;
import unir.store.products.repository.GalleryRepository;
import unir.store.products.service.Interface.IGalleryService;

@Service
public class GalleryImpl implements IGalleryService{

    @Autowired
    private GalleryRepository galleryRepository;

    @Override
    public List<Gallery> getAllGallery() throws GenericException {
        return this.galleryRepository.findAll();
    }

    @Override
    public GalleryDTO createGallery(GalleryDTO gallery) throws GenericException {
        try {
            Product product = Product.builder()
                            .idProduct(gallery.getIdProduct())
                            .build();
            Gallery galleryEntity = Gallery.builder()
                                    .detalle(gallery.getDetalle())
                                    .url(gallery.getUrl())
                                    .es_principal(gallery.getEs_principal())
                                    .product(product)
                                    .build();
            this.galleryRepository.save(galleryEntity);
            gallery = GalleryDTO.builder()
                            .url(galleryEntity.getUrl())
                            .es_principal(galleryEntity.getEs_principal())
                            .idProduct(galleryEntity.getProduct().getIdProduct())
                            .build();
            return gallery;
        } catch (Exception e) {
            throw new GenericException(e.getMessage(), null);
        }
    }

    @Override
    public Gallery getGalleryById(String idGallery) throws GenericException {
        try {
            Long galleryId=Long.valueOf(idGallery);
            return this.galleryRepository.findById(galleryId).get();
        } catch (Exception e) {
            throw new GenericException(e.getMessage(), null);
        }
    }
    
    @Override
    public List<GalleryDTO> getGalleryByProduct(String idProduct) throws GenericException {
        try {
            Long productId=Long.valueOf(idProduct);
            Product product = Product.builder()
                            .idProduct(productId)
                            .build();
            List<Gallery> galleryList = this.galleryRepository.findByProduct(product);
            List<GalleryDTO> resultado = new ArrayList<>();
            for (Gallery gallery : galleryList) {
                GalleryDTO galleryDTO = GalleryDTO.builder()
                                .url(gallery.getUrl())
                                .es_principal(gallery.getEs_principal())
                                .idProduct(product.getIdProduct())
                                .id(gallery.getIdGallery())
                                .build();
                resultado.add(galleryDTO);
            }
            return resultado;
        } catch (Exception e) {
            throw new GenericException(e.getMessage(), null);
        }
    }

    @Override
    public Boolean removeGalleryById(String idGallery) throws GenericException {
        try {
            Gallery gallery = this.galleryRepository.findById(Long.valueOf(idGallery)).orElse(null);
            if (gallery == null) {
                return Boolean.FALSE;
            }
            this.galleryRepository.delete(gallery);
            return Boolean.TRUE;
        } catch (Exception e) {
            throw new GenericException(e.getMessage(), null);
        }
    }

    @Override
    public Boolean removeGalleryByProduct(String idProduct) throws GenericException {
        try {
            Long productId=Long.valueOf(idProduct);
            Product product = Product.builder()
                            .idProduct(productId)
                            .build();
            List<Gallery> galleryList = this.galleryRepository.findByProduct(product);
            if (galleryList.isEmpty()) {
                return Boolean.FALSE;
            }
            this.galleryRepository.deleteAll(galleryList);
            return Boolean.TRUE;
        } catch (Exception e) {
            throw new GenericException(e.getMessage(), null);
        }
    }

    
}
