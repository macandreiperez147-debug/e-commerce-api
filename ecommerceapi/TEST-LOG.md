# API TEST LOG - Ecommerce API

## 1. GET All Products
- Endpoint: GET /api/v1/products
- Status: 200 OK
- Result: Returned list of products

## 2. GET Product by ID
- Endpoint: GET /api/v1/products/1
- Status: 200 OK
- Result: Product returned successfully

## 3. POST Product
- Endpoint: POST /api/v1/products
- Status: 201 Created
- Result: Product successfully created

## 4. PUT Product
- Endpoint: PUT /api/v1/products/1
- Status: 200 OK
- Result: Product replaced successfully

## 5. PATCH Product
- Endpoint: PATCH /api/v1/products/1
- Status: 200 OK
- Result: Product partially updated

## 6. DELETE Product
- Endpoint: DELETE /api/v1/products/1
- Status: 204 No Content
- Result: Product deleted successfully

## 7. FILTER Products
- Endpoint: GET /api/v1/products/filter?filterType=category&filterValue=Electronics
- Status: 200 OK
- Result: Filtered products returned

## 8. ERROR TEST - Invalid ID
- Endpoint: GET /api/v1/products/999
- Status: 404 Not Found
- Result: ProductNotFoundException triggered

## 9. ERROR TEST - Invalid Input
- Endpoint: POST /api/v1/products
- Status: 400 Bad Request
- Result: Validation error returned