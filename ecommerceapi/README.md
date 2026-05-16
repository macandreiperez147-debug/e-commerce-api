Ecommerce API - Spring Boot Project
Project Overview

This project is a RESTful Ecommerce API built using Spring Boot.

It demonstrates:

CRUD operations for products
Input validation
Session-based authentication
Role-based authorization
Secure REST API design
Tech Stack
Java
Spring Boot
Spring Web
Spring Security
Spring Validation
Spring Data JPA
MySQL
Lombok
Gradle
Security Architecture

This application uses session-based authentication provided by Spring Security.

When a user logs in, the server authenticates the credentials and creates an HTTP session. A session ID (JSESSIONID) is generated and stored in a cookie on the client side.

The browser automatically includes this cookie in every request. The server uses the session ID to identify the authenticated user and determine whether they are authenticated.

If the session is valid, access is granted. If the session is missing or invalid, access is denied with HTTP 401 Unauthorized or 403 Forbidden.

Authentication state is stored on the server side, while the client only stores the session identifier.

Validation Rules
User
Username: must be between 8 and 20 characters (required)
Email: must be a valid email format (required)
Password: must not be empty and is stored using BCrypt encryption
Product
Name: required
Description: required
Price: must be greater than 0
Stock: must be 0 or higher
Image URL: optional depending on implementation
Order
Must be created by an authenticated user
Must reference valid product IDs
Cannot be created without a valid session
API Reference
Public Endpoints
POST /api/v1/auth/register → Create new user
POST /login → Authenticate user and create session (JSESSIONID)
GET /api/v1/products → Retrieve all products
Protected Endpoints
POST /api/v1/orders → Create order (requires authentication)
GET /api/v1/orders → View user orders (requires authentication)
Admin Endpoints
DELETE /api/v1/products/{id} → Delete product (requires ADMIN role)
Sample Request (Register User)
{
  "username": "andrei_perez",
  "email": "andrei@example.com",
  "password": "password123"
}
Sample Validation Error Response
{
  "errors": [
    "Username must be between 8 and 20 characters",
    "Email is required"
  ],
  "status": 400
}
Known Limitations
Uses MySQL/local database setup required
No JWT authentication (session-based only)
Frontend is basic HTML only
Author

Mac Andrei Perez
WS101 - IT2B