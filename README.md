# Spring-Boot with Micronaut-Data

This is a sample Micronaut Spring Boot Application.

Framework: Micronaut Spring Boot
Database: H2

## Project Requirements

1. Java 17 or greater

## Project Structure

```
`-- src/main/java
    `-- com.example.readingisgood
        |-- authentication
        |   `-- BasicAıthProvider
        |-- controller
        |   |-- BookController
        |   |-- CustomerController
        |   |-- OrderController
        |   |-- StatisticsController
        |-- entity
        |   `-- Book
        |   `-- Customer
        |   `-- Order
        |-- exception
        |   `-- RestErrorHandler
        |   `-- RestResponse
        |   `-- RestResponseBuilder
        |-- repository
        |   `-- BookRepository
        |   `-- CustomerRepository
        |   `-- OrderRepository
        |   `-- StatisticsRepository
        `-- Application
```

 * `Application` is the starting class of this application. This starts the Spring Boot application.
 * `Book`, `Customer`, `Order`  is the micronaut-data-based entity used by their repositories.
 * `BookController` is the entry point for the API process.
 * `BookRepository` is micronaut-data `JdbcRepository`.
 * `Exception` is for handle exception.
 
```

### Known Issues

-- 1-) Secure endpoints are failed because loginController is not found.

loginController is which generetad by micronaut-security is not found for request. Also security annotations don't work properly. Suggestion to fix that is create a new projet with features.

-- 2-) Order design is not perfect.

Order entity design is not perfect. It is better that if it has order-items. But when I add orderItems, saving order process throw an exception. Exception is about stackOverflow. It can be about lombok library because when I remove it, save process can complete properly.

-- 3-) Statistics Query could not executed.

Query annotation is not working for OrderRepository. All basics query cannot executed also. My query was 

' SELECT 
  MONTH(order.dateCreated) as Month, 
  COUNT(order.id) as totalOrderCount, 
  SUM(orderItem.quantity) as totalBookCount, 
  SUM(orderItem.totalAmount) as totalPurchasedAmount 
FROM 
  Order order 
  LEFT JOIN orderItem orderItem ON order.id = orderItem.order_id 
GROUP BY 
  Month(order.dateCreated) 
order By 
  MONTH(order.dateCreated) ") '
  
  
### Postman

I have also added a postman collection under `./postman/` directory. This collection contains the saving and retrieving 
of book. You can import this into your postman and run the test to see how it exactly works.