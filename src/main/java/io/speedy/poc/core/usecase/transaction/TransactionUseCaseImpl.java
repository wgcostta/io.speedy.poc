package io.speedy.poc.core.usecase.transaction;

import com.google.gson.Gson;
import io.speedy.poc.core.ports.out.sender.RestSenderClient;
import io.speedy.poc.core.usecase.transaction.transferobject.pageresponse.PageResponse;
import io.speedy.poc.core.usecase.transaction.transferobject.transaction.TransactionResponse;
import io.speedy.poc.core.usecase.transaction.enums.*;
import io.speedy.poc.core.ports.in.transaction.transferobject.pageresponseto.PageResponseTO;
import io.speedy.poc.core.ports.in.transaction.transferobject.transactionresponseto.TransactionResponseTO;
import io.speedy.poc.infra.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class TransactionUseCaseImpl implements TransactionUseCase {

    @Autowired
    RestSenderClient restSenderClient;

    @Autowired
    private ModelMapper modelMapper;

    @Value("${api.speedy.transaction.path}")
    private String path;

    @Value("${api.speedy.transaction_list.path}")
    private String pathList;

    @Override
    public PageResponseTO getList(Date fromDate, Date toDate, String status, String operation, Integer merchantId, Integer acquirerId, String paymentMethod, String errorCode, String filterField, String filterValue, Integer page, String authorization) throws ExecutionException, InterruptedException {
        this.validateStatus(status);
        this.validateErrorCode(errorCode);
        this.validateOperation(operation);
        this.validateFilterField(filterField);
        this.validatePaymentMethod(paymentMethod);
        this.validateParameters(fromDate, toDate, status, operation, merchantId, acquirerId, paymentMethod, errorCode, filterField, filterValue, page);

        CompletableFuture.runAsync(() -> log.info("Validation Finished"));
        String response =
                restSenderClient.post(this.getUriParameters(
                        fromDate, toDate, status, operation, merchantId, acquirerId, paymentMethod, errorCode, filterField, filterValue, page
                ), authorization, path + pathList);

        Gson gson = new Gson();
        Optional<PageResponse> pageResponseOptional =
                Optional.ofNullable(gson.fromJson(response, PageResponse.class));

        if (pageResponseOptional.isPresent())
            return PageResponseTO.from(pageResponseOptional.get());
        return new PageResponseTO();
    }

    @Override
    public TransactionResponseTO findByTransactionId(String transactionId, String authorization) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("transactionId", transactionId);
        String response =
                restSenderClient.post(
                        parameters, authorization, path
                );

        Gson gson = new Gson();
        Optional<TransactionResponse> transactionResponse =
                Optional.ofNullable(gson.fromJson(response, TransactionResponse.class));

        if (transactionResponse.isPresent())
            return this.modelMapper.map(transactionResponse.get(), TransactionResponseTO.class);
        return new TransactionResponseTO();
    }

    private Map<String, String> getUriParameters(Date fromDate, Date toDate, String status, String operation, Integer merchantId, Integer acquirerId, String paymentMethod, String errorCode, String filterField, String filterValue, Integer page) {
        Map<String, String> parameters = new HashMap<>();
        if (fromDate != null)
            parameters.put("fromDate", new SimpleDateFormat("yyyy-MM-dd").format(fromDate));
        if (toDate != null)
            parameters.put("toDate", new SimpleDateFormat("yyyy-MM-dd").format(toDate));
        if (status != null)
            parameters.put("status", status);
        if (operation != null)
            parameters.put("operation", operation);
        if (merchantId != null)
            parameters.put("merchantId", merchantId.toString());
        if (acquirerId != null)
            parameters.put("acquirerId", acquirerId.toString());
        if (paymentMethod != null)
            parameters.put("paymentMethod", paymentMethod);
        if (errorCode != null)
            parameters.put("errorCode", errorCode);
        if (filterField != null)
            parameters.put("filterField", filterField);
        if (filterValue != null)
            parameters.put("filterValue", filterValue);
        if (page != null)
            parameters.put("page", page.toString());
        return parameters;
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