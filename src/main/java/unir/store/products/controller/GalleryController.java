package unir.store.products.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import unir.store.products.dto.GalleryDTO;
import unir.store.products.entity.Gallery;
import unir.store.products.exception.GenericException;
import unir.store.products.service.Interface.IGalleryService;
import unir.store.products.utils.GenericBasicResponse;

@RestController
@RequestMapping("/gallery")
public class GalleryController {
    @Autowired
    private IGalleryService galleryService;
    
    public GalleryController(IGalleryService galleryService) {
        this.galleryService = galleryService;
    }
    private GenericBasicResponse<GalleryDTO> response;


    @GetMapping
    public List<Gallery> getGallery() throws GenericException {
        return this.galleryService.getAllGallery();
    }

    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericBasicResponse<GalleryDTO> createGallery(@RequestBody GalleryDTO request) throws GenericException {
        response = new GenericBasicResponse<>();
        response.setData(this.galleryService.createGallery(request));
        return response;
    }
}