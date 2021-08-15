package io.speedy.poc.core.ports.in.transaction.transferobject.transactionresponseto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TransactionResponseTO {
    public FxResponseTO fx;
    public CustomerInfoResponseTO customerInfo;
    public MerchantResponseTO merchant;
    public TransactionSubClassTO transaction;
}



