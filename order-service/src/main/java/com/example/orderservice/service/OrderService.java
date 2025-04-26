package com.example.orderservice.service;

import com.example.orderservice.model.Order;
import com.example.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;

    @PostConstruct
    public void init() {
        // 如果数据库中没有订单，则创建一些示例订单
        if (orderRepository.count() == 0) {
            // 创建5个示例订单
            createSampleOrder("John Doe", "Laptop", 1, 1299.99);
            createSampleOrder("Jane Smith", "Smartphone", 2, 799.99);
            createSampleOrder("Mike Johnson", "Headphones", 3, 149.99);
            createSampleOrder("Sarah Williams", "Tablet", 1, 499.99);
            createSampleOrder("David Brown", "Smartwatch", 2, 249.99);
        }
    }
    
    private void createSampleOrder(String customerName, String productName, int quantity, double totalAmount) {
        Order order = new Order();
        order.setOrderNumber(generateOrderNumber());
        order.setCustomerName(customerName);
        order.setProductName(productName);
        order.setQuantity(quantity);
        order.setTotalAmount(totalAmount);
        order.setStatus("PENDING");
        orderRepository.save(order);
    }

    public Order createOrder(Order order) {
        order.setOrderNumber(generateOrderNumber());
        order.setStatus("PENDING");
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public Order updateOrder(Long id, Order orderDetails) {
        Order order = getOrderById(id);
        order.setCustomerName(orderDetails.getCustomerName());
        order.setProductName(orderDetails.getProductName());
        order.setQuantity(orderDetails.getQuantity());
        order.setTotalAmount(orderDetails.getTotalAmount());
        return orderRepository.save(order);
    }

    private String generateOrderNumber() {
        return "ORD-" + UUID.randomUUID().toString().substring(0, 8);
    }
} 