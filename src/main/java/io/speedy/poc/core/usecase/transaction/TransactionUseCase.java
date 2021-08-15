package io.speedy.poc.core.usecase.transaction;

import java.util.Date;
import java.util.concurrent.ExecutionException;

public interface TransactionUseCase {
    void getList(Date fromDate,
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
}
