package example.micronaut.readingisgood.controller;

import example.micronaut.readingisgood.entity.Customer;
import example.micronaut.readingisgood.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    CustomerRepository customerRepository;

    @Test
    void createCustomerTest() {
        Customer customerTemp = new Customer();
        customerTemp.setEmail("test@test.com");
        customerTemp.setName("testName");
        Customer customer = customerRepository.save(customerTemp);
        assertNotNull(customer.getId());
        Customer duplicatedCustomer = new Customer();
        duplicatedCustomer.setEmail("test@test.com");
        duplicatedCustomer.setName("testName");
        ResponseEntity<Customer> test = postForEntity(duplicatedCustomer);
        assertNull(test.getBody().getId());
    }

    private ResponseEntity<Customer> postForEntity(Customer customer) {
        return restTemplate.postForEntity(customerRequestUriString(), customer, Customer.class);
    }

    private String customerRequestUriString() {
        return UriComponentsBuilder.fromUriString("http://localhost:" + port)
                .path("customer/")
                .build()
                .toUriString();
    }
}
