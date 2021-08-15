package io.speedy.poc.core.ports.out.login;

import io.speedy.poc.core.ports.out.login.transferobject.AccessToken;

import java.util.Optional;

public interface RestSenderPortLogin {
    Optional<AccessToken> getAccessToken(String email, String password);
}
