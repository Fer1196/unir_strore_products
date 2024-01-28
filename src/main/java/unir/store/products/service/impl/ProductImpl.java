package unir.store.products.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import unir.store.products.dto.ProductDTO;
import unir.store.products.entity.Category;
import unir.store.products.entity.Product;
import unir.store.products.exception.GenericException;
import unir.store.products.repository.ProductRepository;
import unir.store.products.service.Interface.IProductService;

@Service
public class ProductImpl implements IProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() throws GenericException {
        return this.productRepository.findAll();
    }

    @Override
    public ProductDTO createProduct(ProductDTO product) throws GenericException {
       try {
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
                                    .thumbnail("hila")
                                    .build();
            this.productRepository.save(productEntity);
            product = ProductDTO.builder()
                            .idProduct(productEntity.getIdProduct())
                            .title(productEntity.getTitle())
                            .description(productEntity.getDescription())
                            .idCategory(productEntity.getCategory().getIdCategory())
                            .price(productEntity.getPrice())
                            .stock(productEntity.getStock())
                            .brand(productEntity.getBrand())
                            .thumbnail(productEntity.getThumbnail())
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

    // @Override
    // public List<Product> getProductByCategory(String category) throws GenericException {
    //    try {
    //         return this.productRepository.findByCategory(category);
    //     } catch (Exception e) {
    //         throw new GenericException(e.getMessage(), null);
    //     }
    // }
}
