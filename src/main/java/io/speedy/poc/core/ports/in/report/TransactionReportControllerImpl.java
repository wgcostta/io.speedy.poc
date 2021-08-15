package io.speedy.poc.core.ports.in.report;

import io.speedy.poc.core.usecase.report.ReportUseCase;
import io.speedy.poc.core.usecase.report.transferobject.ReportTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionReportControllerImpl implements TransactionReportController {
    @Autowired
    private ReportUseCase reportUseCase;

    @PostMapping(path = "report")
    public ResponseEntity<?> report(@RequestParam(value = "fromDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,
                                    @RequestParam(value = "toDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate,
                                    @RequestParam(value = "merchant", required = false) Integer merchant,
                                    @RequestParam(value = "acquirer", required = false) Integer acquirer,
                                    @RequestHeader("Authorization") String authorization
    ) {
        ReportTO reportTO = reportUseCase.getReport(fromDate, toDate, merchant, acquirer, authorization);
        return ResponseEntity.ok(reportTO);
    }
}
