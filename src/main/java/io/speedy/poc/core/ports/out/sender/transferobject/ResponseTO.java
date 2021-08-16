package io.speedy.poc.core.ports.out.sender.transferobject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTO {
    private Integer code;
    private String body;
}
