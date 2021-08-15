package io.speedy.poc.core.ports.in.report;

import org.springframework.http.ResponseEntity;

import java.util.Date;

public interface TransactionReportController {
    ResponseEntity<?> report(Date fromDate,
                             Date toDate,
                             Integer merchant,
                             Integer acquirer,
                             String authorization
    );
}
