package com.cpyproject2spring.demo2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cpyproject2spring.demo2.entities.Product;
import com.cpyproject2spring.demo2.entities.ResponseMassage;
import com.cpyproject2spring.demo2.service.ProductService;

// Step 1: Set up the controller, service + serviceImp, and repository

@RestController
public class ProductController {

    // Dependency Injection
    @Autowired
    private ProductService productService;

    //APIs: insert a product
    @PostMapping("/products")
    public ResponseEntity<ResponseMassage> insertProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    //APIs: get all products
    @GetMapping("/products")
    public List<Product> getProducts() {
        return productService.fetchProductList();
    }

    //APIs: insert a product
    @GetMapping("/products/{id}")
    public ResponseEntity<ResponseMassage> getProductById(@PathVariable("id") Long productId) {
        return productService.getProductById(productId);
    }

    @GetMapping("/products/name/{name}")
    public ResponseEntity<ResponseMassage> getProductByName(@PathVariable("name") String productName) {
        return productService.fetchProductListName(productName);
    }
    
    // Update a product by id
    @PutMapping("/products/{id}")
    public ResponseEntity<ResponseMassage> updateProductById(@PathVariable("id") Long productId, @RequestBody Product newProduct) {
        return productService.updateProductById(productId, newProduct);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<ResponseMassage> deleteProductById(@PathVariable("id") Long productId) {
        return productService.deleteProductById(productId);
    }
    
}
