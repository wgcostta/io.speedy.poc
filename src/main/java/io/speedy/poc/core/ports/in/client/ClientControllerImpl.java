package io.speedy.poc.core.ports.in.client;

import io.speedy.poc.core.usecase.client.ClientUseCase;
import io.speedy.poc.core.usecase.transaction.TransactionUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/client")
public class ClientControllerImpl implements ClientController {
    @Autowired
    private ClientUseCase clientUseCase;

    @PostMapping
    public ResponseEntity<?> postByTransactionId(@RequestParam(value = "transactionId") String transactionId,
                                                 @RequestHeader("Authorization") String authorization
    ) {
        return ResponseEntity.ok(
                clientUseCase.postByTransactionId(transactionId, authorization)
        );
    }
}
