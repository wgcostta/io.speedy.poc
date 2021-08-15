package io.speedy.poc.core.usecase.transaction.transferobject.pageresponseto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class AcquirerTO {
    private int id;
    private String name;
    private String code;
    private String type;
}
