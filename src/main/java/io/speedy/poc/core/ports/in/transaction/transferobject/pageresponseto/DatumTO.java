package io.speedy.poc.core.ports.in.transaction.transferobject.pageresponseto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.speedy.poc.core.usecase.transaction.transferobject.pageresponse.Datum;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
public class DatumTO {
    private FxTO fx;
    private CustomerInfoTO customerInfo;
    private MerchantTO merchant;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private IpnTO ipn;
    private TransactionTO transaction;
    private AcquirerTO acquirer;
    private boolean refundable;

    public static List<DatumTO> from(List<Datum> data) {
        return data.stream().map(DatumTO::fromItem)
                .collect(Collectors.toList());
    }

    private static DatumTO fromItem(Datum itemData) {
        return DatumTO.builder()
                .fx(new FxTO(itemData.getFx().getMerchant()))
                .customerInfo(
                        CustomerInfoTO.builder()
                                .number(itemData.getCustomerInfo().getNumber())
                                .email(itemData.getCustomerInfo().getEmail())
                                .billingFirstName(itemData.getCustomerInfo().getBillingFirstName())
                                .billingLastName(itemData.getCustomerInfo().getBillingLastName())
                                .build()
                )
                .merchant(
                        MerchantTO.from(itemData.getMerchant())
                )
                .ipn((itemData.getIpn() != null) ? IpnTO.from(itemData.getIpn()) : null)
                .transaction(new TransactionTO(MerchantTO.from(itemData.getTransaction().getMerchant())))
                .acquirer(
                        AcquirerTO.builder()
                                .id(itemData.getAcquirer().getId())
                                .name(itemData.getAcquirer().getName())
                                .code(itemData.getAcquirer().getCode())
                                .type(itemData.getAcquirer().getType())
                                .build()
                )
                .refundable(
                        itemData.isRefundable()
                )
                .build();
    }


}
