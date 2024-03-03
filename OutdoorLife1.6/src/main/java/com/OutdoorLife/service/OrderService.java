package com.OutdoorLife.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OutdoorLife.entity.OrderEntity;
import com.OutdoorLife.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // Create an order
    public OrderEntity createOrder(OrderEntity orderEntity) {
        return orderRepository.save(orderEntity);
    }

    // Read an order by ID
    public Optional<OrderEntity> getOrderById(Long orderId) {
        return orderRepository.findById(orderId);
    }

    // Update an order
    public OrderEntity updateOrder(OrderEntity orderEntity) {
        return orderRepository.save(orderEntity);
    }

    // Delete an order by ID
    public void deleteOrderById(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    // Get all orders
    public List<OrderEntity> getAllOrders() {
        Iterable<OrderEntity> orderIterable = orderRepository.findAll();
        List<OrderEntity> orderList = new ArrayList<>();
        orderIterable.forEach(orderList::add);
        return orderList;
    }
}