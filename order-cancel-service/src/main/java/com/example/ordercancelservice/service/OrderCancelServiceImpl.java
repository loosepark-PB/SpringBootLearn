package com.example.ordercancelservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Service
public class OrderCancelServiceImpl implements OrderCancelService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ResponseEntity<String> cancelOrder(Long orderId) {
        String orderServiceUrl = "http://localhost:8080/orders/" + orderId;
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        // Create request body
        String requestBody = "{\"status\": \"CANCELLED\"}";
        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        
        try {
            // Send PUT request to update order status
            restTemplate.exchange(
                orderServiceUrl, 
                HttpMethod.PUT, 
                request, 
                String.class
            );
            
            return ResponseEntity.ok("Order cancelled successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to cancel order: " + e.getMessage());
        }
    }
} 