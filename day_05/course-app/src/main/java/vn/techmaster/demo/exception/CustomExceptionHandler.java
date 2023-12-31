package vn.techmaster.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import vn.techmaster.demo.response.ErrorResponse;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(ResouceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResouceNotFoundException e) {
        return new ResponseEntity<>(
                new ErrorResponse(HttpStatus.NOT_FOUND, e.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }
    @ExceptionHandler(MethodArgumentNotValidException.class) // Xử lý validation
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(
                new ErrorResponse(HttpStatus.BAD_REQUEST, errors),
                HttpStatus.BAD_REQUEST
        );
    }
}
