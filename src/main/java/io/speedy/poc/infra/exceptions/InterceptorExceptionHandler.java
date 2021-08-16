package io.speedy.poc.infra.exceptions;


import io.speedy.poc.infra.exceptions.models.ApiExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@ControllerAdvice
public class InterceptorExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiExceptionResponse> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        ApiExceptionResponse err = ApiExceptionResponse.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(e.getMessage())
                .timeStamp(System.currentTimeMillis())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(ParametersIncorrectException.class)
    public ResponseEntity<ApiExceptionResponse> resourceNotFound(ParametersIncorrectException e, HttpServletRequest request) {
        ApiExceptionResponse err = ApiExceptionResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .timeStamp(System.currentTimeMillis())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(UnauthorizedUserException.class)
    public ResponseEntity<ApiExceptionResponse> resourceNotFound(UnauthorizedUserException e, HttpServletRequest request) {
        ApiExceptionResponse err = ApiExceptionResponse.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .message(e.getMessage())
                .timeStamp(System.currentTimeMillis())
                .build();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);
    }

    @ExceptionHandler(ReportException.class)
    public ResponseEntity<ApiExceptionResponse> resourceNotFound(ReportException e, HttpServletRequest request) {
        ApiExceptionResponse err = ApiExceptionResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .timeStamp(System.currentTimeMillis())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
}


