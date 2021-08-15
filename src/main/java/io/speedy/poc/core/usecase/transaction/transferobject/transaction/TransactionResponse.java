package io.speedy.poc.core.ports.out.transaction.transferobject.transaction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TransactionResponse {
    private FxResponse fx;
    private CustomerInfoResponse customerInfo;
    private MerchantResponse merchant;
    private TransactionSubClass transaction;
}



