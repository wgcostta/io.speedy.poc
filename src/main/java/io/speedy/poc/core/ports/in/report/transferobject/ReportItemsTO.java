package io.speedy.poc.core.ports.in.report.transferobject;

import io.speedy.poc.core.usecase.report.transferobject.ReportItems;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReportItemsTO {
    private Integer count;
    private BigDecimal total;
    private String currency;

    public static List<ReportItemsTO> from(List<ReportItems> reponse) {
        return reponse.stream().map(ReportItemsTO::fromItem).collect(Collectors.toList());
    }

    private static ReportItemsTO fromItem(ReportItems item) {
        return new ReportItemsTO(item.getCount(), item.getTotal(), item.getCurrency());
    }
}
