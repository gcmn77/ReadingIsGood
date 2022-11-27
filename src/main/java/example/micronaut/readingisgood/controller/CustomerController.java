package example.micronaut.readingisgood.controller;

import example.micronaut.readingisgood.entity.Customer;
import example.micronaut.readingisgood.repository.CustomerRepository;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@Secured(SecurityRule.IS_AUTHENTICATED)
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;
    @GetMapping("/customer")
    public Customer getCustomer() {
        Customer customer= new Customer();
        customer.setName("test");
        return customer;
    }

    @PostMapping("/customer")
    public Customer save(@RequestBody @Validated Customer customer) {
        if (customerRepository.existsEmailBy(customer.getEmail())) {
            throw new ResponseStatusException(INTERNAL_SERVER_ERROR, "email.already.registered");
        }
        return customerRepository.save(customer);
    }
}
