package com.assignment.ecommerce.service;

import com.assignment.ecommerce.exception.ProductAlreadyDeletedException;
import com.assignment.ecommerce.model.Product;
import com.assignment.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product findProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product updateProduct(Product product) {
        Product existingProduct = productRepository.findById(product.getId()).orElse(null);
        if (existingProduct != null) {
            existingProduct.setProductName(product.getProductName());
            existingProduct.setProductNumber(product.getProductNumber());
            return productRepository.save(existingProduct);
        }
        return null;
    }

    @Override
    public ResponseEntity<String> deleteProduct(Long id) throws ProductAlreadyDeletedException {
        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isPresent()) {
            productRepository.deleteById(id);
            return ResponseEntity.ok("product number " + id + " deleted");
        } else {
            // Check if product was previously deleted
            if (productRepository.existsById(id)) {
                throw new ProductAlreadyDeletedException("Product with id " + id + " already deleted");
            } else {
                throw new ProductAlreadyDeletedException("Product with id " + id + " not found or already deleted");
            }
        }
    }
}