package spring.boot.ServiceImplementation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.boot.Entity.ProductEntity;
import spring.boot.Repository.ProductRepository;
import spring.boot.Service.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductEntity addProduct(ProductEntity product) {
        return productRepository.save(product);
    }

    @Override
    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public ProductEntity getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public ProductEntity updateProduct(ProductEntity product) {
        System.out.println("updating:...");
        return productRepository.save(product);  // save werkt ook voor updates
    }

    @Override
    public boolean deleteProductById(Long id) {
        if (productRepository.existsById(id)) {
            System.out.println("deleted");
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}