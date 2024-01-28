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
import unir.store.products.utils.StoreValidators;

@Service
public class ProductImpl implements IProductService {
    @Autowired
    private ProductRepository productRepository;
    private IGalleryService galleryService;
    @Autowired
    StoreValidators storeValidators;
    public ProductImpl(IGalleryService galleryService) {
        this.galleryService = galleryService;
    }

    @Override
    public List<ProductDTO> getAllProducts() throws GenericException {
        try {
            List<ProductDTO> products = new ArrayList<>();
            List<Product> productsEntity = this.productRepository.findAll();
            for (Product product : productsEntity) {
                List<GalleryDTO> listGallery = this.galleryService.getGalleryByProduct(product.getIdProduct().toString());
                ProductDTO productDTO = storeValidators.formatProductDTO(product, listGallery);
                products.add(productDTO);
            }
            return products;
        } catch (Exception e) {
            throw new GenericException(e.getMessage(), null);
        }
    }

    @Override
    public ProductDTO createProduct(ProductDTO product) throws GenericException {
        try {
            List<GalleryDTO> listGalleryDTO = new ArrayList<>();
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
                GalleryDTO responseGallery = this.galleryService.createGallery(gallery);
                listGalleryDTO.add(responseGallery);
            }
            product = storeValidators.formatProductDTO(productEntity, listGalleryDTO);
            return product;
        } catch (Exception e) {
            throw new GenericException(e.getMessage(), null);
        }
    }

    @Override
    public Boolean removeProductById(String idProduct) throws GenericException {
        try {
            Long productId = Long.valueOf(idProduct);
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
            if (products.size() > 0) {
                this.productRepository.deleteAll(products);
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new GenericException(e.getMessage(), null);
        }
    }

    @Override
    public ProductDTO getProductById(String idProduct) throws GenericException {
        try {
            Long productId = Long.valueOf(idProduct);
            Product product = this.productRepository.findById(productId).get();
            List<GalleryDTO> listGallery = this.galleryService.getGalleryByProduct(product.getIdProduct().toString());
            return storeValidators.formatProductDTO(product, listGallery);
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
    public List<ProductDTO> getProductByCategory(String category) throws GenericException {
        try {
            List<ProductDTO> products = new ArrayList<>();
            Long categoryId = Long.valueOf(category);
            List<Product> product = this.productRepository.getAllProductsByCategoryId(categoryId);
            for (Product productItem : product) {
                List<GalleryDTO> listGallery = this.galleryService.getGalleryByProduct(productItem.getIdProduct().toString());
                ProductDTO productDTO = storeValidators.formatProductDTO(productItem, listGallery);
                products.add(productDTO);
            }
            return products;
        } catch (Exception e) {
            throw new GenericException(e.getMessage(), null);
        }
    }

    @Override
    public ProductDTO editStockProduct(ProductDTO productDto) throws GenericException {

        try {

            Long productId = Long.valueOf(productDto.getIdProduct());

            Product product = this.productRepository.findById(productId).get();
            if (product.getStock() == 0) {
                return null;
            }
            
            this.productRepository.updateStock(product.getIdProduct(), product.getStock() - productDto.getStock());
            ProductDTO responseProductDTO = this.getProductById(productId.toString());

            return responseProductDTO;

        } catch (Exception e) {
            throw new GenericException(e.getMessage(), null);
        }

    }

    @Override
    public List<ProductDTO> editMasiveStockProduct(List<ProductDTO> productDto) throws GenericException {
        try {
            List<ProductDTO> productUpdate = new ArrayList<>();
            for (ProductDTO productItem : productDto) {
                Long productId = Long.valueOf(productItem.getIdProduct());

                Product product = this.productRepository.findById(productId).get();
                if (product.getStock() == 0) {
                    break;
                }
                this.productRepository.updateStock(product.getIdProduct(), product.getStock() - productItem.getStock());
                ProductDTO responseProductDTO = this.getProductById(productId.toString());
                productUpdate.add(responseProductDTO);
            }

            return productUpdate;

        } catch (Exception e) {
            throw new GenericException(e.getMessage(), null);
        }

    }
}
