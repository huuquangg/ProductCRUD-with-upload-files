package com.cpyproject2spring.demo2.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cpyproject2spring.demo2.entities.Product;
import com.cpyproject2spring.demo2.entities.ResponseMassage;

public interface ProductService {

    public ResponseEntity<ResponseMassage> saveProduct(Product product);

    public List<Product> fetchProductList();

    public ResponseEntity<ResponseMassage> getProductById(Long productId);

    public ResponseEntity<ResponseMassage> fetchProductListName(String productName);

    public ResponseEntity<ResponseMassage> updateProductById(Long productId, Product newProduct);

    public ResponseEntity<ResponseMassage> deleteProductById(Long productId);
}
