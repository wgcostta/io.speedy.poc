package io.speedy.poc.core.ports.in.login;

import io.speedy.poc.core.ports.in.login.transferobject.AccessTokenTO;
import org.springframework.http.ResponseEntity;

public interface LoginController {
    ResponseEntity<AccessTokenTO> login(String email,
                                        String password);
}
