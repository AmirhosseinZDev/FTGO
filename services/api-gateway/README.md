# API Gateway for FTGO

This is the API Gateway service for the FTGO (Food to Go) microservices architecture. It provides centralized authentication, authorization, and routing for all microservices.

## Features

- **Centralized Authentication**: JWT token validation for all protected endpoints
- **Service Routing**: Routes requests to appropriate microservices based on URL patterns
- **CORS Support**: Configured for cross-origin requests
- **Rate Limiting**: Built-in support for request throttling
- **Load Balancing**: Automatic load balancing across service instances
- **Health Checks**: Actuator endpoints for monitoring

## Architecture

The API Gateway acts as the single entry point for all client requests and routes them to the appropriate microservices:

```
Client -> API Gateway (Port 8080) -> {
  /api/users/**      -> User Service (Port 8081)
  /api/orders/**     -> Order Service (Port 8082) 
  /api/restaurants/** -> Restaurant Service (Port 8084)
  /api/couriers/**   -> Courier Service (Port 8083)
  /api/auth/**       -> User Service (Authentication endpoints)
}
```

## Configuration

### Service URLs
- User Service: `http://localhost:8081`
- Order Service: `http://localhost:8082`
- Restaurant Service: `http://localhost:8084`
- Courier Service: `http://localhost:8083`

### Public Endpoints (No Authentication Required)
- `POST /api/auth/login`
- `POST /api/auth/register`
- `/actuator/**`

### Protected Endpoints
All other endpoints require a valid JWT token in the Authorization header:
```
Authorization: Bearer <JWT_TOKEN>
```

## Running the Service

### Prerequisites
- Java 21
- Maven 3.6+
- All microservices running on their respective ports

### Build and Run
```bash
mvn clean compile
mvn spring-boot:run
```

The service will start on port 8080.

### Health Check
```
GET http://localhost:8080/actuator/health
```

## Usage Examples

### Authentication
```bash
# Login
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"user@example.com","password":"password"}'
```

### Making Authenticated Requests
```bash
# Get user profile
curl -X GET http://localhost:8080/api/users/profile \
  -H "Authorization: Bearer <JWT_TOKEN>"

# Create order
curl -X POST http://localhost:8080/api/orders \
  -H "Authorization: Bearer <JWT_TOKEN>" \
  -H "Content-Type: application/json" \
  -d '{"restaurantId":1,"items":[...]}'
```

## Security Features

- JWT token validation on all protected endpoints
- Automatic user context injection into downstream service headers
- CORS protection
- Request/response logging for audit trails

## Monitoring

The gateway exposes actuator endpoints for monitoring:
- `/actuator/health` - Health status
- `/actuator/info` - Application information
- `/actuator/gateway/routes` - Current gateway routes