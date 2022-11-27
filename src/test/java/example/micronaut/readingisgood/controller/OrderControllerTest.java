package example.micronaut.readingisgood.controller;

import example.micronaut.readingisgood.entity.Book;
import example.micronaut.readingisgood.entity.Customer;
import example.micronaut.readingisgood.entity.order.Order;
import example.micronaut.readingisgood.repository.BookRepository;
import example.micronaut.readingisgood.repository.CustomerRepository;
import example.micronaut.readingisgood.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    CustomerRepository customerRepository;

    @Test
    void orderTest() {
        Order order1 = new Order();
        order1.setOrderDate(new Date());
        order1.setQuantity(2);
        order1.setTotalAmount(new BigDecimal(100));
        Book book1 = new Book();
        book1.setStock(3);
        book1.setName("book1");
        book1 = bookRepository.save(book1);
        Customer customer1 = new Customer();
        customer1.setEmail("test1@test.com");
        customer1.setName("test");
        customer1 = customerRepository.save(customer1);
        ResponseEntity<Order> savedOrder = postForEntity(order1);
        assertNull(savedOrder.getBody().getId());
        order1.setCustomer(customer1);
        savedOrder = postForEntity(order1);
        assertNull(savedOrder.getBody().getId());
        order1.setBook(book1);
        savedOrder = postForEntity(order1);
        assertNotNull(savedOrder.getBody().getId());
        savedOrder = postForEntity(order1);
        assertNull(savedOrder.getBody().getId());
    }

    private ResponseEntity<Order> postForEntity(Order order) {
        return restTemplate.postForEntity(orderRequestUriString(), order, Order.class);
    }

    private String orderRequestUriString() {
        return UriComponentsBuilder.fromUriString("http://localhost:" + port)
                .path("order/")
                .build()
                .toUriString();
    }
}
