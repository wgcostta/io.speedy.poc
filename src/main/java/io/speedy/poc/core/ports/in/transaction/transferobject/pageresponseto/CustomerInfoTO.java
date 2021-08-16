package io.speedy.poc.core.ports.in.transaction.transferobject.pageresponseto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
public class CustomerInfoTO {
    private String number;
    private String email;
    private String billingFirstName;
    private String billingLastName;
}
