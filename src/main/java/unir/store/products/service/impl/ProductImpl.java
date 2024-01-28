package unir.store.products.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import unir.store.products.dto.ProductDTO;
import unir.store.products.dto.GalleryDTO;
import unir.store.products.entity.Category;
import unir.store.products.entity.Product;
import unir.store.products.exception.GenericException;
import unir.store.products.repository.ProductRepository;
import unir.store.products.service.Interface.IGalleryService;
import unir.store.products.service.Interface.IProductService;

@Service
public class ProductImpl implements IProductService {
    @Autowired
    private ProductRepository productRepository;
    private IGalleryService galleryService;
    public ProductImpl(IGalleryService galleryService) {
        this.galleryService = galleryService;
    }


    @Override
    public List<Product> getAllProducts() throws GenericException {
        return this.productRepository.findAll();
    }

    @Override
    public ProductDTO createProduct(ProductDTO product) throws GenericException {
       try {
            List<GalleryDTO> galleryDTO= new ArrayList<>();
            Category category = Category.builder()
                            .idCategory(product.getIdCategory())
                            .build();
            Product productEntity = Product.builder()
                                    .title(product.getTitle())
                                    .description(product.getDescription())
                                    .category(category)
                                    .price(product.getPrice())
                                    .stock(product.getStock())
                                    .brand(product.getBrand())
                                    .thumbnail(product.getThumbnail())
                                    .build();
            this.productRepository.save(productEntity);
           
            for (GalleryDTO gallery : product.getGallery()) {
                gallery = GalleryDTO.builder()
                                .detalle(gallery.getDetalle())
                                .url(gallery.getUrl())
                                .es_principal(gallery.getEs_principal())
                                .idProduct(productEntity.getIdProduct())
                                .build();
                GalleryDTO respuesta = this.galleryService.createGallery(gallery);
                galleryDTO.add(respuesta);
            }

            product = ProductDTO.builder()
            .idProduct(productEntity.getIdProduct())
            .title(productEntity.getTitle())
            .description(productEntity.getDescription())
            .idCategory(productEntity.getCategory().getIdCategory())
            .price(productEntity.getPrice())
            .stock(productEntity.getStock())
            .brand(productEntity.getBrand())
            .thumbnail(productEntity.getThumbnail())
            .gallery(galleryDTO)
            .build();

            return product;
        } catch (Exception e) {
            throw new GenericException(e.getMessage(), null);
        }
    }

    
    @Override
    public Boolean removeProductById(String idProduct) throws GenericException {
       try {
            Long productId=Long.valueOf(idProduct);
            this.productRepository.deleteById(productId);
            return true;
        } catch (Exception e) {
            throw new GenericException(e.getMessage(), null);
        }
    }

    @Override
    public Boolean removeProductByTitle(String title) throws GenericException {
       try {
            List<Product> products = this.productRepository.findByTitle(title);
            if(products.size()>0){
                this.productRepository.deleteAll(products);
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new GenericException(e.getMessage(), null);
        }
    }

    @Override
    public Product getProductById(String idProduct) throws GenericException {
       try {
            Long productId=Long.valueOf(idProduct);
            return this.productRepository.findById(productId).get();
        } catch (Exception e) {
            throw new GenericException(e.getMessage(), null);
        }   
    }

    @Override
    public List<Product> getProductByTitle(String title) throws GenericException {
        try {
            return this.productRepository.findByTitle(title);
        } catch (Exception e) {
            throw new GenericException(e.getMessage(), null);
        }
    }

    @Override
    public List<Product> getProductByCategory(String category) throws GenericException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProductByCategory'");
    }

    @Override
    public ProductDTO editStockProduct(ProductDTO productDto) throws GenericException {

        try {

                Long productId=Long.valueOf(productDto.getIdProduct());
              
                Product product =  this.productRepository.findById(productId).get();
                if(product.getStock() ==0){
                    return null; 
                }
                ProductDTO productUpdate = null;
                this.productRepository.updateStock(product.getIdProduct(), product.getStock() - productDto.getStock());
           

                return productUpdate; 
           
        } catch (Exception e) {
            throw new UnsupportedOperationException("Unimplemented method 'editStockProduct'");
        }
    
    }


    @Override
    public List<ProductDTO> editMasiveStockProduct(List<ProductDTO> productDto) throws GenericException {
        try {
            List<ProductDTO> productUpdate = new ArrayList<>();
            for (ProductDTO productItem : productDto) {
                Long productId=Long.valueOf(productItem.getIdProduct());
          
                Product product =  this.productRepository.findById(productId).get();
                if(product.getStock() ==0){
                    break;
                }
                this.productRepository.updateStock(product.getIdProduct(), product.getStock() - productItem.getStock());
                Product responseProduct = this.productRepository.findById(productId).get();
                productItem = ProductDTO.builder()
                            .idProduct(responseProduct.getIdProduct())
                            .title(responseProduct.getTitle())
                            .description(responseProduct.getDescription())
                            .idCategory(responseProduct.getCategory().getIdCategory())
                            .price(responseProduct.getPrice())
                            .stock(responseProduct.getStock())
                            .brand(responseProduct.getBrand())
                            .thumbnail(responseProduct.getThumbnail())
                            .build();
                productUpdate.add(productItem);
            }
            
            return productUpdate; 
       
    } catch (Exception e) {
        throw new UnsupportedOperationException("Unimplemented method 'editStockProduct'");
    }

    }
}
