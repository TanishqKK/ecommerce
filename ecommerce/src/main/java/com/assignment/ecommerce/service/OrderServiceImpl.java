package com.assignment.ecommerce.service;

import com.assignment.ecommerce.exception.OrderNotFoundException;
import com.assignment.ecommerce.model.Order;
import com.assignment.ecommerce.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order findOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public Order updateOrder(Order order) {
        Order existingOrder = orderRepository.findById(order.getId()).orElse(null);
        if (existingOrder != null) {
            existingOrder.setId(order.getId());
            existingOrder.setOrderDate(order.getOrderDate());
            existingOrder.setTotalCost(order.getTotalCost());
            existingOrder.setAddress(order.getAddress());

            return orderRepository.save(existingOrder);
        }
        return null;
    }

    @Override
    public ResponseEntity<String> deleteOrder(Long id) throws OrderNotFoundException {
        Optional<Order> orderOptional = orderRepository.findById(id);

        if (orderOptional.isPresent()) {
            orderRepository.deleteById(id);
            return ResponseEntity.ok("Order number " + id + " deleted");
        } else {
            // Check if order was previously deleted
            if (orderRepository.existsById(id)) {
                throw new OrderNotFoundException("Order with id " + id + " already deleted");
            } else {
                throw new OrderNotFoundException("Order with id " + id + " not found or already deleted");
            }
        }
    }
}