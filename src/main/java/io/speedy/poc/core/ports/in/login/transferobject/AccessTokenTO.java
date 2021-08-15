package io.speedy.poc.core.ports.in.login.transferobject;

import lombok.Data;

@Data
public class AccessTokenTO {
    private String token;
    private String status;

    public AccessTokenTO(String access_token, String status) {
        this.token = access_token;
        this.status = status;
    }

    public AccessTokenTO() {

    }
}
