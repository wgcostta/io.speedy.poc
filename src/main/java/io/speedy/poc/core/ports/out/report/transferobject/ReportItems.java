package io.speedy.poc.core.ports.out.report.transferobject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReportItems {
    private Integer count;
    private BigDecimal total;
    private String currency;
}
