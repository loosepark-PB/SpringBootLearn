package com.example.ordercancelservice.service;

import org.springframework.http.ResponseEntity;

public interface OrderCancelService {
    ResponseEntity<String> cancelOrder(Long orderId);
}
