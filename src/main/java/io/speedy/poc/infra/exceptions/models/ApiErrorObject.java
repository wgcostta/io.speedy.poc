package io.speedy.poc.infra.exceptions.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiErrorObject {

    private final String message;
    private final String field;
    private final Object parameter;
}