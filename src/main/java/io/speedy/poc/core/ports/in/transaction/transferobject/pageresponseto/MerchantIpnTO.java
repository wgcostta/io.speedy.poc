package io.speedy.poc.core.ports.in.transaction.transferobject.pageresponseto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.speedy.poc.core.usecase.transaction.transferobject.pageresponse.MerchantIpn;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MerchantIpnTO {
    private String transactionId;
    private String referenceNo;
    private int amount;
    private String currency;
    private int date;
    private String code;
    private String message;
    private String operation;
    private String type;
    private String status;
    private Object customData;
    private String chainId;
    private String paymentType;
    private String token;
    private int convertedAmount;
    private String convertedCurrency;
    private String iPNUrl;
    private String ipnType;

    public static MerchantIpnTO from(MerchantIpn merchant) {
        return MerchantIpnTO.builder()
                .transactionId(merchant.getTransactionId())
                .referenceNo(merchant.getReferenceNo())
                .amount(merchant.getAmount())
                .currency(merchant.getCurrency())
                .date(merchant.getDate())
                .code(merchant.getCode())
                .message(merchant.getMessage())
                .operation(merchant.getOperation())
                .type(merchant.getType())
                .status(merchant.getStatus())
                .customData(merchant.getCustomData())
                .chainId(merchant.getChainId())
                .paymentType(merchant.getPaymentType())
                .token(merchant.getToken())
                .convertedAmount(merchant.getConvertedAmount())
                .convertedCurrency(merchant.getConvertedCurrency())
                .iPNUrl(merchant.getIPNUrl())
                .ipnType(merchant.getIpnType())
                .build();
    }
}
