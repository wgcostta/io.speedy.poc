package io.speedy.poc.core.usecase.login.transferobject;

import lombok.Data;

@Data
public class AccessToken {
    private String token;
    private String status;
}
