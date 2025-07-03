# FTGO

FTGO (Food to Go) is a microservices-based food delivery platform demonstrating modern architecture patterns including API Gateway, authentication, and authorization.

## Architecture

The system consists of the following microservices:

- **API Gateway** (Port 8080) - Single entry point with authentication and routing
- **User Service** (Port 8081) - User management and authentication  
- **Order Service** (Port 8082) - Order processing and management
- **Restaurant Service** (Port 8084) - Restaurant information and menu management
- **Courier Service** (Port 8083) - Delivery management

## Features

- **Centralized API Gateway**: Single entry point for all client requests
- **JWT Authentication**: Secure token-based authentication across all services
- **Service Routing**: Intelligent routing to appropriate microservices
- **CORS Support**: Cross-origin resource sharing configuration
- **Health Monitoring**: Actuator endpoints for service monitoring

## Quick Start

1. Start all microservices on their respective ports
2. Access the API Gateway at `http://localhost:8080`
3. Use `/api/auth/login` for authentication
4. All other endpoints require JWT authentication

## API Endpoints

### Authentication (Public)
- `POST /api/auth/login` - User login
- `POST /api/auth/register` - User registration

### Protected Endpoints
- `/api/users/**` - User management
- `/api/orders/**` - Order operations  
- `/api/restaurants/**` - Restaurant operations
- `/api/couriers/**` - Courier operations

All protected endpoints require `Authorization: Bearer <JWT_TOKEN>` header.

## Development

Each service is implemented using Spring Boot 3.4.4 with Java 17/21 and managed as Git submodules for independent development and deployment.