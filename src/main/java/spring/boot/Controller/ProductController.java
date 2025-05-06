package spring.boot.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import spring.boot.Entity.ProductEntity;
import spring.boot.Service.ProductService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<ProductEntity> addProduct(
            @RequestParam("name") String name,
            @RequestParam("category") String category,
            @RequestParam("price") Double price,
            @RequestParam("image") MultipartFile image) throws IOException {

        ProductEntity product = new ProductEntity();
        product.setName(name);
        product.setCategory(category);
        product.setPrice(price);

        // Zet afbeelding om naar byte[] en sla op
        product.setImage(image.getBytes());

        ProductEntity savedProduct = productService.addProduct(product);

        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @GetMapping
    public List<ProductEntity> getAllProducts() {
        return productService.getAllProducts();  // Haalt de producten op van de service
    }
}