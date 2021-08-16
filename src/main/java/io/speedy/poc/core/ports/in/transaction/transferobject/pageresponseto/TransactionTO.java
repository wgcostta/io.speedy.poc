package io.speedy.poc.core.ports.in.transaction.transferobject.pageresponseto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionTO {
    private MerchantTO merchant;
}
