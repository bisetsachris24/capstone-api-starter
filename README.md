# Easy Shop E-Commerce API

## Overview

Easy Shop is a Spring Boot REST API that serves as the backend for an e-commerce web application. The API allows users to browse products, search and filter products, manage shopping carts, and authenticate using JWT tokens. Administrators can manage products and categories through secured endpoints.

This project was completed as part of a software development capstone focused on debugging existing functionality, implementing new features, and testing API endpoints.

## Features

### Authentication

* User Registration
* User Login
* Role-Based Authorization (ADMIN and USER)

### Product 

* View all products
* View product by ID
* Search and filter products
* Create products (Admin only)
* Update products (Admin only)
* Delete products (Admin only)

### Category 

* View all categories
* View category by ID
* View products by category
* Create categories (Admin only)
* Update categories (Admin only)
* Delete categories (Admin only)

### Bug Fixes

* Fixed product search functionality returning incomplete results
* Fixed product stock update issue where inventory changes were not saved

### Testing

* Unit testing with JUnit and Mockito
* API endpoint testing using Insomnia

## Technologies Used

* Java
* Spring Boot
* Spring Security
* Spring Data JPA
* MySQL
* Maven
* JWT Authentication
* JUnit
* Mockito
* Insomnia

## API Endpoints

### Authentication

| Method | Endpoint  |
| ------ | --------- |
| POST   | /register |
| POST   | /login    |

### Categories

| Method | Endpoint                  |
| ------ | ------------------------- |
| GET    | /categories               |
| GET    | /categories/{id}          |
| GET    | /categories/{id}/products |
| POST   | /categories               |
| PUT    | /categories/{id}          |
| DELETE | /categories/{id}          |

### Products

| Method | Endpoint       |
| ------ | -------------- |
| GET    | /products      |
| GET    | /products/{id} |
| POST   | /products      |
| PUT    | /products/{id} |
| DELETE | /products/{id} |

## Installation

### Clone the Repository

```bash
git clone https://github.com/your-username/easy-shop-api.git
```

### Configure MySQL

Update the `application.properties` file:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/easyshop
spring.datasource.username=root
spring.datasource.password=your_password
```

### Run the Application

```bash
mvn spring-boot:run
```

The API will be available at:

```text
http://localhost:8080
```

## Demo Users

| Username | Password |
| -------- | -------- |
| admin    | password |
| user     | password |
| george   | password |

## Project Structure

```text
src/main/java
├── controllers
├── services
├── repositories
├── models
├── security
└── configuration
```

## Testing

Use Insomnia or Postman to test all API endpoints. JWT tokens returned from the login endpoint must be included in authorized requests.


