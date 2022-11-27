package example.micronaut.readingisgood.exception;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class RestResponse {
//    private final LocalDateTime timestamp = LocalDateTime.now();
    private int status;
    private String error;
    private String message;
    private String path;
    // getters, setters, etc.

    public static RestResponseBuilder builder() {
        return new RestResponseBuilder();
    }
}
