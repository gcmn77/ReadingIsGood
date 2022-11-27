package example.micronaut.readingisgood.controller;

import example.micronaut.readingisgood.entity.Book;
import example.micronaut.readingisgood.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/book/{id}")
    public Optional<Book> show(@PathVariable("id") Long id) {
        if(!bookRepository.existsById(id)) {
            throw new ResponseStatusException(INTERNAL_SERVER_ERROR, "book.not.found");
        }
        return bookRepository.findById(id);
    }

    @Transactional
    @PostMapping("/book")
    public Book save(@RequestBody @Validated Book book) {
        return bookRepository.save(book);
    }

    @Transactional
    @PutMapping("/book/updateStock/{id}")
    public boolean updateStock(@PathVariable("id") Long id, @RequestBody Book book) {
        bookRepository.updateStockById(id, book.getStock());
        return true;
    }
}
