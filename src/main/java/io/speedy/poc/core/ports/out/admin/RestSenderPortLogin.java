package io.speedy.poc.core.ports.out.admin;

public interface RestSenderPortLogin {
    String getAccessToken(String email, String password);
}
