package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.context.annotation.Bean;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{userId}/orders")
    public String getUserOrders(@PathVariable String userId) {
        String orderServiceUrl = "http://localhost:8082/orders/user/" + userId;
        return restTemplate.getForObject(orderServiceUrl, String.class);
    }

 
}
