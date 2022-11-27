package example.micronaut.readingisgood.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
public class BaseController {
//    @ExceptionHandler()
//    void handleException(Exception exception) {
//        exception.printStackTrace();
//        throw new ResponseStatusException(INTERNAL_SERVER_ERROR, exception.getMessage());
//    }
}
