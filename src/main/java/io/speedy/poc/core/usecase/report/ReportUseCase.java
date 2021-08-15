package io.speedy.poc.core.usecase.report;

import io.speedy.poc.core.usecase.report.transferobject.ReportTO;

import java.util.Date;

public interface ReportUseCase {

    ReportTO getReport(Date fromDate, Date toDate, Integer merchant, Integer acquirer, String authorization);
}
