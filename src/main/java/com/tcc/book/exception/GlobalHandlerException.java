package com.tcc.book.exception;

import com.tcc.book.utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<Response<Void>> handleBookNotFoundException(BookNotFoundException ex) {
        Response<Void> response = new Response<>();
        response.setMessage(ex.getMessage());
        response.setErrorExist(true);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response<Map<String, String>>> handleValidationException(MethodArgumentNotValidException ex) {
        Response<Map<String, String>> response = new Response<>();
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        response.setData(errors);
        response.setMessage("Validation error");
        response.setErrorExist(true);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response<Void>> handleGlobalException(Exception ex, WebRequest request) {
        Response<Void> response = new Response<>();
        response.setMessage("An unexpected error occurred: " + ex.getMessage());
        response.setErrorExist(true);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

