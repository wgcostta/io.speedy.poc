package io.speedy.poc.core.usecase.transaction.transferobject.transaction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionResponse {
    private FxResponse fx;
    private CustomerInfoResponse customerInfo;
    private MerchantResponse merchant;
    private TransactionSubClass transaction;
    private ParentResponse parent;
}



