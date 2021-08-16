package io.speedy.poc.core.ports.in.transaction.transferobject.pageresponseto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
public class AcquirerTO {
    private int id;
    private String name;
    private String code;
    private String type;
}
