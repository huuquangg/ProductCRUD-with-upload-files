package com.cpyproject2spring.demo2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cpyproject2spring.demo2.entities.Product;
import com.cpyproject2spring.demo2.entities.ResponseMassage;
import com.cpyproject2spring.demo2.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;
    
    @Override
    public ResponseEntity<ResponseMassage> saveProduct(Product newProduct) {
        List<Product> insertProducts = productRepository.findByProductName(newProduct.getProductName().trim());
        
        return insertProducts.size() > 0 ? 
                ResponseEntity.status(HttpStatus.CONFLICT).body(
                    new ResponseMassage(HttpStatus.CONFLICT, "Product already exists", null)):
                ResponseEntity.status(HttpStatus.CREATED).body(
                    new ResponseMassage(HttpStatus.CREATED, "Product created", productRepository.save(newProduct)));
    }

    @Override
    public List<Product> fetchProductList() {
        return productRepository.findAll();
    }


    // Format chuarn hoas ResponseMassage
    @Override
    public ResponseEntity<ResponseMassage> getProductById(Long productId) {
        Optional<Product> productIsfinding = productRepository.findById(productId);
        return !productIsfinding.isPresent() ? 
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseMassage(HttpStatus.NOT_FOUND, "Product not found", null)):
                ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseMassage(HttpStatus.OK, "Product found", productIsfinding.get()));
        
    }

    @Override
    public ResponseEntity<ResponseMassage> fetchProductListName(String productName) {
        List<Product> productIsfinding = productRepository.findByProductName(productName.trim());
        return productIsfinding.size() == 0 ? 
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseMassage(HttpStatus.NOT_FOUND, "Product not found", null)):
                ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseMassage(HttpStatus.OK, "Product found", productIsfinding));
    }
    
    @Override
    public ResponseEntity<ResponseMassage> updateProductById(Long productId, Product newProduct) {
        Product updatedProduct = productRepository.findById(productId).map(product -> {
            product.setProductName(newProduct.getProductName());
            product.setProductPrice(newProduct.getProductPrice());
            return productRepository.save(product);
        }).orElseGet(() -> {
            newProduct.setProductId(productId);
            return productRepository.save(newProduct);
        });

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseMassage(HttpStatus.OK, "Product updated", updatedProduct));
    }


    @Override
    public ResponseEntity<ResponseMassage> deleteProductById(Long productId) {
        Optional<Product> productIsFinding = productRepository.findById(productId);
        productRepository.deleteById(productId);
        return !productIsFinding.isPresent() ?
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseMassage(HttpStatus.NOT_FOUND, "Product not found", null)):
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseMassage(HttpStatus.OK, "Product deleted successful" , productIsFinding.get()));
    }
}
