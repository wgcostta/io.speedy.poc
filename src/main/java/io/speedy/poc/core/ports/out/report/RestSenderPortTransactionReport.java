package io.speedy.poc.core.ports.out.report;

import io.speedy.poc.core.ports.out.report.transferobject.Report;

import java.util.Date;
import java.util.Optional;

public interface RestSenderPortTransactionReport {
    Optional<Report> getReport(Date fromDate, Date toDate, Integer merchant, Integer acquirer, String authorization);
}
