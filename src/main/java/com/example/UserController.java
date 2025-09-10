package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    @Value("${order.service.baseurl}")
    private String orderServiceBaseUrl;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * @param userId
     * @return
     */
    @GetMapping("/{userId}/orders")
    public String getUserOrders(@PathVariable String userId) {
        String orderServiceUrl = orderServiceBaseUrl + userId;
        logger.info("Fetching user orders from: {}", orderServiceUrl);
        try {
            String response = restTemplate.getForObject(orderServiceUrl, String.class);
            logger.info("Received response: {}", response);
            return response;
        } catch (Exception e) {
            logger.error("Error fetching user orders: {}", e.getMessage());
            return "Error fetching user orders";
        }
    }

    @GetMapping()
    public String displayHome() {
        logger.info("invoked /users endpoint");
        return "Welcome to User Service!";
    }
}
