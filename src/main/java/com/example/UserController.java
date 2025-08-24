package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.context.annotation.Bean;

@RestController
@RequestMapping("/users")
public class UserController {

    @Value("${order.service.baseurl}")
    private String orderServiceBaseUrl;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{userId}/orders")
    public String getUserOrders(@PathVariable String userId) {
        String orderServiceUrl = orderServiceBaseUrl + userId;
        return restTemplate.getForObject(orderServiceUrl, String.class);
    } 
}
