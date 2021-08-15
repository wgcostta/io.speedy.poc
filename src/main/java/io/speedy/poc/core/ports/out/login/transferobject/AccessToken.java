package io.speedy.poc.core.ports.out.login.transferobject;

import lombok.Data;

@Data
public class AccessToken {
    private String token;
    private String status;
}
