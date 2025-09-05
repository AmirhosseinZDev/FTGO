package com.ftgo.gateway;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
    "services.user-service.url=http://localhost:8081",
    "services.order-service.url=http://localhost:8082",
    "services.restaurant-service.url=http://localhost:8084", 
    "services.courier-service.url=http://localhost:8083"
})
class ApiGatewayApplicationTests {

    @Test
    void contextLoads() {
        // Test that the Spring context loads successfully
    }
}