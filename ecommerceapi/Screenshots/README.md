Database Schema
### Category Table
- id (BIGINT, PK, AUTO_INCREMENT)
- name (VARCHAR)

### Product Table
- id (BIGINT, PK, AUTO_INCREMENT)
- name (VARCHAR, NOT NULL)
- description (TEXT)
- price (DOUBLE, NOT NULL)
- stock (INT, NOT NULL)
- image_url (VARCHAR)
- category_id (BIGINT, FK → category.id)

Relationships
- One Category → Many Products
- Product → Many-to-One Category

API Endpoints
### Products
GET    /api/v1/products → Get all products  
GET    /api/v1/products/{id} → Get product by ID  
POST   /api/v1/products → Create product  
PUT    /api/v1/products/{id} → Update product  
PATCH  /api/v1/products/{id} → Partial update  
DELETE /api/v1/products/{id} → Delete product  

### Filter
GET /api/v1/products/filter?type=category&value=Electronics

### Database Screenshot
![Database](Screenshots/db.png)

### Browser Console Screenshot
![Console](Screenshots/console.png)
![Console](Screenshots/console(1).png)
![Console](Screenshots/console(2).png)
![Console](Screenshots/console(3).png)
![Console](Screenshots/console(4).png)