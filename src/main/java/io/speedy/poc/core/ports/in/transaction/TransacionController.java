package io.speedy.poc.core.ports.in.transaction;

import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.concurrent.ExecutionException;

public interface TransacionController {
    ResponseEntity<?> list(Date fromDate,
                           Date toDate,
                           String status,
                           String operation,
                           Integer merchantId,
                           Integer acquirerId,
                           String paymentMethod,
                           String errorCode,
                           String filterField,
                           String filterValue,
                           Integer page,
                           String authorization) throws ExecutionException, InterruptedException;

    ResponseEntity<?> findByTransactionId(String transactionId,
                                          String authorization);
}
