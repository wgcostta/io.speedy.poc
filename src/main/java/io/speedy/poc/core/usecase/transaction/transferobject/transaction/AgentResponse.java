package io.speedy.poc.core.usecase.transaction.transferobject.transaction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AgentResponse {
    private int id;
    private String customerIp;
    private String customerUserAgent;
    private String merchantIp;
}
