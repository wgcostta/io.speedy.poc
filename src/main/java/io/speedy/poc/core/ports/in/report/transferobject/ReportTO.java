package io.speedy.poc.core.ports.in.report.transferobject;

import io.speedy.poc.core.usecase.report.transferobject.Report;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReportTO {
    private String status;
    private List<ReportItemsTO> reponse;

    public static ReportTO from(Report report) {
        List<ReportItemsTO> reportItemsTOS = ReportItemsTO.from(report.getReponse());
        ReportTO reportTO = new ReportTO(report.getStatus(), reportItemsTOS);
        return reportTO;
    }
}
