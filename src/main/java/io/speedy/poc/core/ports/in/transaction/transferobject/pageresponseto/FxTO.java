package io.speedy.poc.core.usecase.transaction.transferobject.pageresponseto;

import io.speedy.poc.core.ports.out.transaction.transferobject.pageresponse.Merchant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FxTO {
    private Merchant merchant;
}
