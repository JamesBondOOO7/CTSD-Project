package com.spectre.school_app.rest;

import com.spectre.school_app.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice(annotations = RestController.class)
@Order(1)
public class GlobalExceptionRestController extends ResponseEntityExceptionHandler{

    /*
    When users are sending invalid data format to the REST APIs
     */
    protected ResponseEntity<Response> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        Response response = new Response(
                status.toString(),
                ex.getBindingResult().toString());
        log.info(response.toString());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    /*
    When an exception is thrown
     */
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Response> exceptionHandler(Exception exception) {
        Response response = new Response(
                "500",
                exception.getMessage());
        return new ResponseEntity(
                response,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
