package io.speedy.poc.infra.exceptions.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ApiErrorResponse {

    private final String message;
    private final int code;
    private final String status;
    private final String objectName;
    private final List<ApiErrorObject> errors;
}