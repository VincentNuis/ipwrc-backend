package spring.boot.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.boot.Entity.ProductEntity;
import spring.boot.Repository.ProductRepository;

import java.util.List;

public interface ProductService {
    ProductEntity addProduct(ProductEntity product);
    List<ProductEntity> getAllProducts();
    ProductEntity getProductById(Long id);
    ProductEntity updateProduct(ProductEntity product);
    boolean deleteProductById(Long id);
}