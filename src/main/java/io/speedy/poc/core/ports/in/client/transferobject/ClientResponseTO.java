package io.speedy.poc.core.ports.in.client.transferobject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponseTO {
    private CustomerInfoResponseTO customerInfo;
}
