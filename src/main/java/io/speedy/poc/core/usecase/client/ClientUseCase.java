package io.speedy.poc.core.usecase.client;

import io.speedy.poc.core.ports.in.client.transferobject.ClientResponseTO;

public interface ClientUseCase {
    ClientResponseTO postByTransactionId(String transactionId, String authorization);
}
