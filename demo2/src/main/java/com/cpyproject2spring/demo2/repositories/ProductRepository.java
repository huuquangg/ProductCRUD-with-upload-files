package com.cpyproject2spring.demo2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cpyproject2spring.demo2.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

    public List<Product> findByProductName(String productName);
    
}
