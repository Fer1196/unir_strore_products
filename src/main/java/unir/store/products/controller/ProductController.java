package unir.store.products.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import unir.store.products.dto.ProductDTO;
import unir.store.products.entity.Product;
import unir.store.products.exception.GenericException;
import unir.store.products.service.Interface.IProductService;
import unir.store.products.utils.GenericBasicResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService productService;
    private GenericBasicResponse<ProductDTO> response;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }
    @GetMapping
    public List<Product> getProducts() throws GenericException {
        return this.productService.getAllProducts();
    }
    @GetMapping("id/{idProduct}")
    public Product getProductById(@RequestParam String idProduct) throws GenericException {
        return this.productService.getProductById(idProduct);
    }
    

}
