package spring.boot.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.boot.Entity.ProductEntity;
import spring.boot.Repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductEntity addProduct(ProductEntity productEntity) {
        return productRepository.save(productEntity);  // Slaat het product op in de database
    }
    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();  // Haalt alle producten op uit de database
    }
}