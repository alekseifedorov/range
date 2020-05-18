package my.assignment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler {

    private Logger log = LoggerFactory.getLogger(MyExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleError(Exception e) {

        log.error("Error: ", e.getCause(), e);

        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
