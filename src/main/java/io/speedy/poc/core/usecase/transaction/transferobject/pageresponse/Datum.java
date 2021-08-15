package io.speedy.poc.core.usecase.transaction.transferobject.pageresponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Datum {
    private Fx fx;
    private CustomerInfo customerInfo;
    private Merchant merchant;
    private Ipn ipn;
    private Transaction transaction;
    private Acquirer acquirer;
    private boolean refundable;
}
