package com.workshop.springbootrestapi.controller;

import com.workshop.springbootrestapi.model.Product;
import com.workshop.springbootrestapi.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private static final String API_KEY = "123456";

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    private void validateApiKey(String apiKey) {
        if (!API_KEY.equals(apiKey)) {
            throw new RuntimeException("Invalid API Key");
        }
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(
            @RequestHeader("API-Key") String apiKey,
            @Valid @RequestBody Product product) {

        validateApiKey(apiKey);
        return ResponseEntity.ok(productService.addProduct(product));
    }

    @GetMapping
    public List<Product> getAll() {
        return productService.getAllProducts();
    }

    @GetMapping("/{name}")
    public Product getByName(@PathVariable String name) {
        return productService.getProductByName(name);
    }

    @PutMapping("/{name}")
    public Product update(@PathVariable String name, @RequestBody Product product) {
        return productService.updateProduct(name, product);
    }

    @DeleteMapping("/{name}")
    public void delete(@PathVariable String name) {
        productService.deleteProduct(name);
    }

    @GetMapping("/category/{category}")
    public List<Product> getByCategory(@PathVariable String category) {
        return productService.getProductsByCategory(category);
    }

    @GetMapping("/price")
    public List<Product> getByPriceRange(
            @RequestParam double min,
            @RequestParam double max) {
        return productService.getProductsByPriceRange(min, max);
    }
}