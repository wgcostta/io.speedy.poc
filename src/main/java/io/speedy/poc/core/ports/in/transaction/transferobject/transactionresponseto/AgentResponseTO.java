package io.speedy.poc.core.ports.in.transaction.transferobject.transactionresponseto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AgentResponseTO {
    private int id;
    private String customerIp;
    private String customerUserAgent;
    private String merchantIp;
}
