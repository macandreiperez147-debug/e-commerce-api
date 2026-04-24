# Ecommerce API - Spring Boot Project

## Project Overview
This project is a simple RESTful Ecommerce API built using Spring Boot.  
It manages products using in-memory storage (ArrayList) and demonstrates CRUD operations, filtering, validation, and global exception handling.

---

## Tech Stack
- Java
- Spring Boot
- Spring Web
- Spring Validation
- Lombok
- Gradle

---

## How to Run the Application

### 1. Clone the repository
```bash
git clone <your-repo-url>

API Endpoints Reference

Get All Products
Method: GET
URL: /api/v1/products
Description: Retrieve all products
Response: 200 OK

Get Product by ID
Method: GET
URL: /api/v1/products/{id}
Description: Get a single product by ID
Response: 200 OK / 404 Not Found

Create Product
Method: POST
URL: /api/v1/products
Description: Create a new product
Response: 201 Created

Sample Request:

{
  "name": "Laptop",
  "description": "Gaming laptop",
  "price": 1200,
  "category": "Electronics",
  "stock": 5,
  "imageUrl": "image.jpg"
}

Update Product (PUT)
Method: PUT
URL: /api/v1/products/{id}
Description: Replace entire product
Response: 200 OK / 404 Not Found

Partial Update (PATCH)
Method: PATCH
URL: /api/v1/products/{id}
Description: Update specific fields
Response: 200 OK / 404 Not Found

Delete Product
Method: DELETE
URL: /api/v1/products/{id}
Description: Delete product
Response: 204 No Content / 404 Not Found

Filter Products
Method: GET
URL: /api/v1/products/filter?filterType=category&filterValue=Electronics
Description: Filter products by category or name
Response: 200 OK
Sample Response
{
  "id": 1,
  "name": "Laptop",
  "description": "Gaming laptop",
  "price": 1200,
  "category": "Electronics",
  "stock": 5,
  "imageUrl": "image.jpg"
}
Error Responses
404 Not Found
{
  "timestamp": "2026-04-24T12:00:00",
  "status": 404,
  "error": "NOT FOUND",
  "message": "Product not found"
}
400 Bad Request (Validation Error)
{
  "timestamp": "2026-04-24T12:00:00",
  "status": 400,
  "error": "VALIDATION ERROR",
  "message": "name: Product name is required;"
}

Known Limitations
Uses in-memory storage (ArrayList) instead of a database
Data resets when the application restarts
No authentication or security layer implemented
Designed for learning purposes only

## Author
## Co-authored-by: Angelica Naza
Name: Mac Andrei Perez
Course: WS101
Section: IT-2B