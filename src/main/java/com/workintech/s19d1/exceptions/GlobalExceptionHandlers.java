package com.workintech.s19d1.exceptions;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;
import java.time.LocalDateTime;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandlers {

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleException(ApiException apiException) {
        log.error("Apiexception occured: " + apiException);
        return new ResponseEntity<>(
                new ExceptionResponse(
                        apiException.getMessage(),
                        apiException.getHttpStatus().value(),
                        LocalDateTime.now()
                ),
                apiException.getHttpStatus()
        );
    }


    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleException(Exception exception) {
        log.error("Apiexception occurred: " + exception);

        return new ResponseEntity<>(
                new ExceptionResponse(
                        exception.getMessage(),
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        LocalDateTime.now()
                ),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
