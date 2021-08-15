package io.speedy.poc.core.ports.out.transaction.transferobject.pageresponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Merchant {
    private int originalAmount;
    private String originalCurrency;
    private int id;
    private String name;
    private String referenceNo;
    private String status;
    private String operation;
    private String message;
    private String created_at;
    private String transactionId;
}
