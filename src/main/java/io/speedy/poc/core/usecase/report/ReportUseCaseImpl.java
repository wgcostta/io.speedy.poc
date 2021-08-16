package io.speedy.poc.core.usecase.report;

import com.google.gson.Gson;
import io.speedy.poc.core.ports.in.report.transferobject.ReportTO;
import io.speedy.poc.core.ports.out.sender.RestSenderClient;
import io.speedy.poc.core.ports.out.sender.transferobject.ResponseTO;
import io.speedy.poc.core.usecase.report.transferobject.Report;
import io.speedy.poc.infra.exceptions.ParametersIncorrectException;
import io.speedy.poc.infra.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ReportUseCaseImpl implements ReportUseCase {

    @Autowired
    RestSenderClient restSenderClient;

    @Value("${api.speedy.transactions_report.path}")
    private String path;

    @Override
    public ReportTO getReport(Date fromDate, Date toDate, Integer merchant, Integer acquirer, String authorization) {
        this.validateParameters(fromDate, toDate, merchant, acquirer);

        ResponseTO response =
                restSenderClient.post(
                        this.getUriParameters(fromDate, toDate, merchant, acquirer), authorization, path
                );

        Gson gson = new Gson();
        Optional<Report> reportOptional =
                Optional.ofNullable(gson.fromJson(response.getBody(), Report.class));

        if (reportOptional.isPresent())
            return ReportTO.from(reportOptional.get());
        return new ReportTO();
    }

    private void validateParameters(Date fromDate, Date toDate, Integer merchant, Integer acquirer) {
        if (fromDate == null && toDate == null && merchant == null && acquirer == null)
            throw new ParametersIncorrectException("Enter at least one of the parameters: fromDate | toDate | merchant | acquirer");
    }

    private Map<String, String> getUriParameters(Date fromDate, Date toDate, Integer merchant, Integer acquirer) {
        Map<String, String> parameters = new HashMap<>();
        if (fromDate != null)
            parameters.put("fromDate", new SimpleDateFormat("yyyy-MM-dd").format(fromDate));
        if (toDate != null)
            parameters.put("toDate", new SimpleDateFormat("yyyy-MM-dd").format(toDate));
        if (acquirer != null)
            parameters.put("acquirer", acquirer.toString());
        if (merchant != null)
            parameters.put("merchant", merchant.toString());
        return parameters;
    }
}
