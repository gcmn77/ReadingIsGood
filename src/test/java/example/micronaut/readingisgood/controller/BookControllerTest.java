package example.micronaut.readingisgood.controller;

import example.micronaut.readingisgood.entity.Book;
import example.micronaut.readingisgood.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    BookRepository bookRepository;

    @Test
    void createBookTest() {
        Book java18Temp = new Book();
        java18Temp.setName("java18");
        java18Temp.setStock(77);
        Book java18 = bookRepository.save(java18Temp);
        ResponseEntity<Book> book = getForEntity(java18.getId().toString());
          assertNotNull(book.getBody().getId());
        Book java19 = new Book();
        java18.setName("java19");
        Exception thrown = assertThrows(
                Exception.class,
                () -> bookRepository.save(java19),
                "Expected save() to throw, but it didn't"
        );
        assertTrue(thrown.getMessage().contains("stock"));
    }

    @Test
    void updateStockTest() {
        Book java18Temp = new Book();
        java18Temp.setName("java18");
        java18Temp.setStock(77);
        Book java18 = bookRepository.save(java18Temp);
        ResponseEntity<Book> book = getForEntity(java18.getId().toString());
        assertEquals(book.getBody().getStock(), 77);
        java18.setStock(1);
        bookRepository.updateStockById(java18.getId(), java18.getStock());
        book = getForEntity(java18.getId().toString());
        assertEquals(book.getBody().getStock(), 1);
    }

    private ResponseEntity<Book> getForEntity(String bookId) {
        return restTemplate.getForEntity(booksRequestUriString(bookId), Book.class);
    }

    private String booksRequestUriString(String bookId) {
        return UriComponentsBuilder.fromUriString("http://localhost:" + port)
                .path("book/" + bookId)
                .build()
                .toUriString();
    }
}
