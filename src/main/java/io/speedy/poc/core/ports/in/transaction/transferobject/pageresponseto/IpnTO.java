package io.speedy.poc.core.ports.in.transaction.transferobject.pageresponseto;

import io.speedy.poc.core.usecase.transaction.transferobject.pageresponse.Ipn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IpnTO {
    private boolean received;
    private boolean sent;
    private MerchantIpnTO merchant;

    public static IpnTO from(Ipn ipn) {
        return new IpnTO(
                ipn.isReceived(),
                ipn.isSent(),
                MerchantIpnTO.from(ipn.getMerchant())
        );
    }
}
