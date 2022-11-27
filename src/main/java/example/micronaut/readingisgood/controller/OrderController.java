package example.micronaut.readingisgood.controller;

import example.micronaut.readingisgood.entity.Customer;
import example.micronaut.readingisgood.entity.order.Order;
import example.micronaut.readingisgood.entity.order.OrderItem;
import example.micronaut.readingisgood.repository.BookRepository;
import example.micronaut.readingisgood.repository.CustomerRepository;
import example.micronaut.readingisgood.repository.OrderItemRepository;
import example.micronaut.readingisgood.repository.OrderRepository;
import io.micronaut.core.convert.format.Format;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
public class OrderController {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    OrderItemRepository orderItemRepository;

    @Transactional
    @PostMapping("/order")
    public Order save(@RequestBody Order order) {
        if(order.getQuantity() < 1) {
            throw new ResponseStatusException(INTERNAL_SERVER_ERROR, "quantity.must.greater.than.zero");
        }
        Long bookId = order.getBook().getId();
        if(!bookRepository.existsById(bookId)) {
            throw new ResponseStatusException(INTERNAL_SERVER_ERROR, "book.not.found");
        }
        if(!customerRepository.existsById(order.getCustomer().getId())) {
            throw new ResponseStatusException(INTERNAL_SERVER_ERROR, "customer.not.found");
        }
        if(!bookRepository.existsByIdAndStockGreaterThan(bookId, order.getQuantity() - 1)) {
            throw new ResponseStatusException(INTERNAL_SERVER_ERROR, "out.of.stock.book");
        }
        bookRepository.stockOutById(bookId, order.getQuantity());
        return orderRepository.save(order);
    }

    @GetMapping("/order/{id}")
    public Optional<Order> show(@PathVariable("id") Long id) {
        return orderRepository.findById(id);
    }

    @GetMapping("/order")
    public List<Order> getOrdersByDateInterval(@Format("yyyy-MM-dd") @RequestParam String startDate, @Format("yyyy-MM-dd")  @RequestParam String endDate) {
        Date start, end;
        try {
            start = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
            end = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
        } catch (Exception e) {
            throw new ResponseStatusException(INTERNAL_SERVER_ERROR, "Date format should be 'yyyy-MM-dd'");
        }
        return orderRepository.findAllByOrderDateAfterAndOrderDateBefore(start, end);
    }

    @GetMapping("/order/customer/{customerId}")
    public List<Order> getOrdersOfCustomer(@PathVariable("customerId") Long customerId) {
        Optional<Customer> customer  = customerRepository.findById(customerId);
        if(customer.get().getEmail().isEmpty()) {
            throw new ResponseStatusException(INTERNAL_SERVER_ERROR, "Customer not found'");
        }
        return orderRepository.findAllByCustomer(customer.get());
    }
}
