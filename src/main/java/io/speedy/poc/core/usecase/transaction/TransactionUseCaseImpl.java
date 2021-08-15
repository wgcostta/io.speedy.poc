package io.speedy.poc.core.usecase.transaction;

import io.speedy.poc.core.ports.out.report.transferobject.Report;
import io.speedy.poc.core.ports.out.transaction.RestSenderPortTransaction;
import io.speedy.poc.core.ports.out.transaction.transferobject.PageResponse;
import io.speedy.poc.core.usecase.transaction.enums.*;
import io.speedy.poc.infra.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class TransactionUseCaseImpl implements TransactionUseCase {

    @Autowired
    RestSenderPortTransaction restSenderPortTransaction;

    @Override
    public  getList(Date fromDate, Date toDate, String status, String operation, Integer merchantId, Integer acquirerId, String paymentMethod, String errorCode, String filterField, String filterValue, Integer page, String authorization) throws ExecutionException, InterruptedException {
        this.validateStatus(status);
        this.validateErrorCode(errorCode);
        this.validateOperation(operation);
        this.validateFilterField(filterField);
        this.validatePaymentMethod(paymentMethod);
        this.validateParameters(fromDate, toDate, status, operation, merchantId, acquirerId, paymentMethod, errorCode, filterField, filterValue, page);

        CompletableFuture.runAsync(() -> log.info("Validation Finished"));
        Optional<PageResponse> pageResponseOptional =
                restSenderPortTransaction.getList(
                        fromDate, toDate, status, operation, merchantId, acquirerId, paymentMethod, errorCode, filterField, filterValue, page,authorization);
        if(pageResponseOptional.isPresent())
            pageResponseOptional.get();
    }

    private void validateParameters(Date fromDate, Date toDate, String status, String operation, Integer merchantId, Integer acquirerId, String paymentMethod, String errorCode, String filterField, String filterValue, Integer page) {
        if (fromDate == null && toDate == null && status == null && operation == null && merchantId == null && acquirerId == null && paymentMethod == null && errorCode == null && filterField == null && filterValue == null && page == null)
            throw new ResourceNotFoundException("Enter at least one of the parameters: fromDate | toDate | merchant | acquirer");
    }

    private void validateStatus(String status) {
        try {
            if (status != null && !status.isEmpty())
                EnumStatus.valueOfLabel(status);
        } catch (Exception ex) {
            log.error("Error convert status" + ex.getMessage());
            throw new ResourceNotFoundException("Status is not valid");
        }
    }

    private void validateOperation(String operation) {
        try {
            if (operation != null && !operation.isEmpty())
                EnumOperation.valueOfLabel(operation);
        } catch (Exception ex) {
            log.error("Error convert operation" + ex.getMessage());
            throw new ResourceNotFoundException("Operation is not valid");
        }
    }

    private void validatePaymentMethod(String paymentMethod) {
        try {
            if (paymentMethod != null && !paymentMethod.isEmpty())
                EnumPaymentMethod.valueOfLabel(paymentMethod);
        } catch (Exception ex) {
            log.error("Error convert Payment Method" + ex.getMessage());
            throw new ResourceNotFoundException("Payment Method is not valid");
        }
    }

    private void validateErrorCode(String errorCode) {
        try {
            if (errorCode != null && !errorCode.isEmpty())
                EnumErrorCode.valueOfLabel(errorCode);
        } catch (Exception ex) {
            log.error("Error convert error code" + ex.getMessage());
            throw new ResourceNotFoundException("Error Code is not valid");
        }
    }

    private void validateFilterField(String filterField) {
        try {
            if (filterField != null && !filterField.isEmpty())
                EnumFilterField.valueOfLabel(filterField);
        } catch (Exception ex) {
            log.error("Error convert filter field" + ex.getMessage());
            throw new ResourceNotFoundException("Filter Field is not valid");
        }
    }
}