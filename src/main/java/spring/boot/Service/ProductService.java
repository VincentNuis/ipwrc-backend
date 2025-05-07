package spring.boot.Service;

import spring.boot.Entity.ProductEntity;

import java.util.List;

public interface ProductService {
    ProductEntity addProduct(ProductEntity product);
    List<ProductEntity> getAllProducts();
    ProductEntity getProductById(Long id);
    ProductEntity updateProduct(ProductEntity product);
    boolean deleteProductById(Long id);
}