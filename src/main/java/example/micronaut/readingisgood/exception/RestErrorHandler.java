package example.micronaut.readingisgood.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
public class RestErrorHandler {
    @ExceptionHandler(ResponseStatusException.class)
    ResponseEntity<?> handleStatusException(ResponseStatusException ex, WebRequest request) {
        return RestResponse.builder()
                .exception(ex)
                .path(request.getDescription(false).substring(4))
                .entity();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class) //todo çalışmıyor.
    ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex, WebRequest request) {
        return  RestResponse.builder()
                .status(INTERNAL_SERVER_ERROR)
                .message(ex.getParameter().toString())
                .path(getPath(request))
                .entity();
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<?> handleAllExceptions(Exception ex, WebRequest request) {
        ex.printStackTrace();
        return handleEveryException(ex, request);
    }

    protected ResponseEntity<RestResponse> handleEveryException(Exception ex, WebRequest request) {
        return RestResponse.builder()
                .status(INTERNAL_SERVER_ERROR)
                .message(ex.getMessage())
                .path(getPath(request))
                .entity();
    }
    private String getPath(WebRequest request) {
        return request.getDescription(false).substring(4);
    }
}
