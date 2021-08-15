package io.speedy.poc.infra.exceptions;


import io.speedy.poc.infra.exceptions.models.ApiErrorObject;
import io.speedy.poc.infra.exceptions.models.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class InterceptorErrorHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<ApiErrorObject> errors = getErrors(ex);
        ApiErrorResponse errorResponse = getErrorResponse(ex, HttpStatus.BAD_REQUEST, errors);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    private ApiErrorResponse getErrorResponse(MethodArgumentNotValidException ex, HttpStatus status, List<ApiErrorObject> errors) {
        return new ApiErrorResponse("Requisição possui campos inválidos", status.value(),
                status.getReasonPhrase(), ex.getBindingResult().getObjectName(), errors);
    }

    private List<ApiErrorObject> getErrors(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors().stream()
                .map(error -> new ApiErrorObject(error.getDefaultMessage(), error.getField(), error.getRejectedValue()))
                .collect(Collectors.toList());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MissingServletRequestParameterException ex) {


        ApiErrorResponse errorResponse = getErrorResponse(ex, HttpStatus.BAD_REQUEST, ex.getParameterName());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    private ApiErrorResponse getErrorResponse(MissingServletRequestParameterException ex, HttpStatus status, String errors) {
        return new ApiErrorResponse("Requisição possui campos inválidos", status.value(),
                status.getReasonPhrase(), ex.getParameterName(), null);
    }

}
