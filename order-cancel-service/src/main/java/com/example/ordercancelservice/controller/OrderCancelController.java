package com.example.ordercancelservice.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.example.ordercancelservice.service.OrderCancelService;

@RestController
@RequestMapping("/api/cancel")
@CrossOrigin(origins = "*") // 允许所有来源的跨域请求
public class OrderCancelController {

    @Autowired
    private OrderCancelService orderCancelService;

    @PostMapping("/{orderId}")
    public ResponseEntity<String> cancelOrder(@PathVariable Long orderId) {
        try {
            return orderCancelService.cancelOrder(orderId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to cancel order: " + e.getMessage());
        }
    }
} 