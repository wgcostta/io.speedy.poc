package io.speedy.poc.core.usecase.transaction.transferobject.pageresponseto;

import io.speedy.poc.core.ports.out.transaction.transferobject.pageresponse.Merchant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class MerchantTO {
    private int originalAmount;
    private String originalCurrency;
    private int id;
    private String name;
    private String referenceNo;
    private String status;
    private String operation;
    private String message;
    private String created_at;
    private String transactionId;

    static MerchantTO from(Merchant merchant) {
        return MerchantTO.builder()
                .created_at(merchant.getCreated_at())
                .originalAmount(merchant.getOriginalAmount())
                .originalCurrency(merchant.getOriginalCurrency())
                .id(merchant.getId())
                .name(merchant.getName())
                .referenceNo(merchant.getReferenceNo())
                .status(merchant.getStatus())
                .operation(merchant.getOperation())
                .message(merchant.getMessage())
                .transactionId(merchant.getTransactionId())
                .build();
    }
}
