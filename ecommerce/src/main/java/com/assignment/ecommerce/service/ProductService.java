package com.assignment.ecommerce.service;

import com.assignment.ecommerce.exception.ProductAlreadyDeletedException;
import com.assignment.ecommerce.model.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product createProduct(Product product);
    Product findProductById(Long id);
    Product updateProduct(Product product);
    ResponseEntity<String> deleteProduct(Long id) throws ProductAlreadyDeletedException;
}