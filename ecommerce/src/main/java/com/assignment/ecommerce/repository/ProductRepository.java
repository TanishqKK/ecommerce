package com.assignment.ecommerce.repository;

import com.assignment.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository <Product , Long> {

}
