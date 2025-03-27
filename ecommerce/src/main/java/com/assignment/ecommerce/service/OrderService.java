package com.assignment.ecommerce.service;

import com.assignment.ecommerce.exception.OrderNotFoundException;
import com.assignment.ecommerce.model.Order;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();
    Order createOrder(Order order);
    Order findOrderById(Long id);
    Order updateOrder(Order order);
    ResponseEntity<String> deleteOrder(Long id) throws OrderNotFoundException;
}