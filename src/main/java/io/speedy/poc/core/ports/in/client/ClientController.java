package io.speedy.poc.core.ports.in.client;

import org.springframework.http.ResponseEntity;

public interface ClientController {
    ResponseEntity<?> postByTransactionId(String transactionId,
                                          String authorization);
}
