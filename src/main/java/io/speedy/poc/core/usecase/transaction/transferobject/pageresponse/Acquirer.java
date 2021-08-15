package io.speedy.poc.core.ports.out.transaction.transferobject.pageresponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Acquirer {
    private int id;
    private String name;
    private String code;
    private String type;
}
