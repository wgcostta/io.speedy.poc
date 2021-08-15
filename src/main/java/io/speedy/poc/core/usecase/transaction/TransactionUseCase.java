package io.speedy.poc.core.usecase.transaction;

import io.speedy.poc.core.ports.in.transaction.transferobject.pageresponseto.PageResponseTO;
import io.speedy.poc.core.ports.in.transaction.transferobject.transactionresponseto.TransactionResponseTO;

import java.util.Date;
import java.util.concurrent.ExecutionException;

public interface TransactionUseCase {
    PageResponseTO getList(Date fromDate,
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

    TransactionResponseTO findByTransactionId(String transactionId, String authorization);
}
