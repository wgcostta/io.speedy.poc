package io.speedy.poc.infra.exceptions.models;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiExceptionResponse {

    private Integer status;
    private String message;
    private Long timeStamp;

}
