package unir.store.products.service.Interface;
import java.util.List;
import org.springframework.stereotype.Service;

import unir.store.products.dto.ProductDTO;
import unir.store.products.entity.Product;
import unir.store.products.exception.GenericException;

@Service
public interface IProductService {
    public List<Product> getAllProducts() throws GenericException;
    public ProductDTO createProduct(ProductDTO product) throws GenericException;
    public Product getProductById(String idProduct) throws GenericException;
    public List<Product> getProductByTitle(String title) throws GenericException;
    public List<Product> getProductByCategory(String category) throws GenericException;
    public Boolean removeProductById(String idProduct) throws GenericException;
    public Boolean removeProductByTitle(String title) throws GenericException;
}