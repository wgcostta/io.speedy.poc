package io.speedy.poc.core.usecase.transferobject;

import lombok.Data;

@Data
public class AccessTokenTO {
    private String access_token;

    public AccessTokenTO(String access_token) {
        this.access_token = access_token;
    }
}
