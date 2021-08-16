package io.speedy.poc.core.ports.in.transaction.transferobject.pageresponseto;

import io.speedy.poc.core.usecase.transaction.transferobject.pageresponse.Merchant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FxTO {
    private Merchant merchant;
}
