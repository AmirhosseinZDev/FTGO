package com.ftgo.gateway.filter;

import com.ftgo.gateway.util.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@TestPropertySource(properties = {
    "services.user-service.url=http://localhost:8081",
    "services.order-service.url=http://localhost:8082",
    "services.restaurant-service.url=http://localhost:8084", 
    "services.courier-service.url=http://localhost:8083"
})
class AuthenticationFilterTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void shouldAllowAccessToPublicEndpoints() {
        // Test that public endpoints don't require authentication
        webTestClient.get()
                .uri("/actuator/health")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void shouldRejectRequestsWithoutAuthorizationHeader() {
        // Test that protected endpoints require authentication
        webTestClient.get()
                .uri("/api/users/profile")
                .exchange()
                .expectStatus().isUnauthorized();
    }

    @Test
    void shouldRejectRequestsWithInvalidAuthorizationHeader() {
        // Test that invalid authorization headers are rejected
        webTestClient.get()
                .uri("/api/users/profile")
                .header("Authorization", "Invalid")
                .exchange()
                .expectStatus().isUnauthorized();
    }

    @Test
    void shouldRejectRequestsWithInvalidJwtToken() {
        // Test that invalid JWT tokens are rejected
        webTestClient.get()
                .uri("/api/users/profile")
                .header("Authorization", "Bearer invalid.jwt.token")
                .exchange()
                .expectStatus().isUnauthorized();
    }
}