package io.speedy.poc.core.ports.in.transaction.transferobject.transactionresponseto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MerchantResponseTO {
    private int originalAmount;
    private String originalCurrency;
    private String name;
    private String referenceNo;
    private int merchantId;
    private String status;
    private String channel;
    private Object customData;
    private String chainId;
    private int agentInfoId;
    private String operation;
    private int fxTransactionId;
    private String updated_at;
    private String created_at;
    private int id;
    private int acquirerTransactionId;
    private String code;
    private String message;
    private String transactionId;
    private AgentResponseTO agent;
}
