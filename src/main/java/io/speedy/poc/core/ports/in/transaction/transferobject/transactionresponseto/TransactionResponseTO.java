package io.speedy.poc.core.ports.in.transaction.transferobject.transactionresponseto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionResponseTO {
    private FxResponseTO fx;
    private CustomerInfoResponseTO customerInfo;
    private MerchantResponseTO merchant;
    private TransactionSubClassTO transaction;
    private ParentResponseTO parent;

}



