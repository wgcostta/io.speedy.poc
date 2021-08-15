package io.speedy.poc.core.usecase.transaction.transferobject.pageresponseto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class CustomerInfoTO {
    private String number;
    private String email;
    private String billingFirstName;
    private String billingLastName;
}
