package io.speedy.poc.core.usecase.transaction.transferobject.pageresponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CustomerInfo {
    private String number;
    private String email;
    private String billingFirstName;
    private String billingLastName;
}
