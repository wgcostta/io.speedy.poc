package io.speedy.poc.core.ports.in.report.transferobject;

import io.speedy.poc.core.usecase.report.transferobject.Report;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReportTO {
    private String status;
    private List<ReportItemsTO> response;

    public static ReportTO from(Report report) {
        List<ReportItemsTO> reportItemsTOS = ReportItemsTO.from(report.getResponse());
        return new ReportTO(report.getStatus(), reportItemsTOS);
    }
}
