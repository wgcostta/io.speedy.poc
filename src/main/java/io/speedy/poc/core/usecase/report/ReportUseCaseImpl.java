package io.speedy.poc.core.usecase.report;

import io.speedy.poc.core.ports.out.report.RestSenderPortTransactionReport;
import io.speedy.poc.core.ports.out.report.transferobject.Report;
import io.speedy.poc.core.ports.out.transaction.transferobject.PageResponse;
import io.speedy.poc.core.usecase.report.transferobject.ReportTO;
import io.speedy.poc.infra.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ReportUseCaseImpl implements ReportUseCase {

    @Autowired
    RestSenderPortTransactionReport restSenderPortTransactionReport;

    @Override
    public ReportTO getReport(Date fromDate, Date toDate, Integer merchant, Integer acquirer, String authorization) {
        this.validateParameters(fromDate, toDate, merchant, acquirer);
        Optional<Report> report = restSenderPortTransactionReport.getReport(fromDate, toDate, merchant, acquirer, authorization);
        if (report.isPresent())
            return ReportTO.from(report.get());
        return new ReportTO();
    }

    private void validateParameters(Date fromDate, Date toDate, Integer merchant, Integer acquirer) {
        if (fromDate == null && toDate == null && merchant == null && acquirer == null)
            throw new ResourceNotFoundException("Enter at least one of the parameters: fromDate | toDate | merchant | acquirer");
    }
}
