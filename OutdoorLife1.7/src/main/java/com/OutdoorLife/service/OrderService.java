package com.OutdoorLife.service;

import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OutdoorLife.LogComponent.LoggerInterceptor;
import com.OutdoorLife.entity.OrderEntity;
import com.OutdoorLife.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private LoggerInterceptor logger;

    // Create an order
    public OrderEntity createOrder(OrderEntity orderEntity) {
    	logger.log(Level.INFO, OrderService.class.getName(), "createOrder", "Order created");
        return orderRepository.save(orderEntity);
    }

    // Read an order by ID
    public Optional<OrderEntity> getOrderById(Long orderId) {
        return orderRepository.findById(orderId);
    }

    // Update an order
    public OrderEntity updateOrder(OrderEntity orderEntity) {
    	logger.log(Level.INFO, OrderService.class.getName(), "updateOrder", "Order updated");
        return orderRepository.save(orderEntity);
    }

    // Delete an order by ID
    public void deleteOrderById(Long orderId) {
        orderRepository.deleteById(orderId);
        logger.log(Level.INFO, OrderService.class.getName(), "deleteOrder", "Order deleted");
    }

    // Get all orders
    public List<OrderEntity> getAllOrders() {
    	logger.log(Level.INFO, OrderService.class.getName(), "getAllOrders", "All orders returned");
        Iterable<OrderEntity> orderIterable = orderRepository.findAll();
        List<OrderEntity> orderList = new ArrayList<>();
        orderIterable.forEach(orderList::add);
        return orderList;
    }
}