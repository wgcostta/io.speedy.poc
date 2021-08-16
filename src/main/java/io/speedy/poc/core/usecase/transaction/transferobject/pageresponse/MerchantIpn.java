package io.speedy.poc.core.usecase.transaction.transferobject.pageresponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MerchantIpn {
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

    @JsonProperty("IPNUrl")
    private String iPNUrl;
    private String ipnType;
}
