package io.speedy.poc.core.ports.in.transaction;

import io.speedy.poc.core.usecase.transaction.TransactionUseCase;
import io.speedy.poc.core.ports.in.transaction.transferobject.pageresponseto.PageResponseTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/v1/transaction")
public class TransacionControllerImpl implements TransacionController {
    @Autowired
    private TransactionUseCase transactionUseCase;

    @Override
    @PostMapping
    public ResponseEntity<?> findByTransactionId(@RequestParam(value = "transactionId") String transactionId,
                                                 @RequestHeader("Authorization") String authorization
    ) {
        return ResponseEntity.ok(
                transactionUseCase.findByTransactionId(transactionId, authorization)
        );
    }

    @Override
    @PostMapping(path = "list")
    public ResponseEntity<?> list(@RequestParam(value = "fromDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,
                                  @RequestParam(value = "toDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate,
                                  @RequestParam(value = "status", required = false) String status,
                                  @RequestParam(value = "operation", required = false) String operation,
                                  @RequestParam(value = "merchantId", required = false) Integer merchantId,
                                  @RequestParam(value = "acquirerId", required = false) Integer acquirerId,
                                  @RequestParam(value = "paymentMethod", required = false) String paymentMethod,
                                  @RequestParam(value = "errorCode", required = false) String errorCode,
                                  @RequestParam(value = "filterField", required = false) String filterField,
                                  @RequestParam(value = "filterValue", required = false) String filterValue,
                                  @RequestParam(value = "page", required = false) Integer page,
                                  @RequestHeader("Authorization") String authorization) throws ExecutionException, InterruptedException {
        PageResponseTO pageResponseTO =
                transactionUseCase.getList(
                        fromDate, toDate, status, operation, merchantId, acquirerId, paymentMethod, errorCode, filterField, filterValue, page, authorization);
        return ResponseEntity.ok(pageResponseTO);
    }
}
