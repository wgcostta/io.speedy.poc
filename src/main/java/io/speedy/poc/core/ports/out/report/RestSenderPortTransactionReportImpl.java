package io.speedy.poc.core.ports.out.report;

import com.google.gson.Gson;
import io.speedy.poc.core.ports.out.report.transferobject.Report;
import io.speedy.poc.infra.exceptions.ReportException;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Component
@Slf4j
public class RestSenderPortTransactionReportImpl implements RestSenderPortTransactionReport {
    @Value("${api.speedy.base_url}")
    private String baseUrl;

    @Value("${api.speedy.base_version}")
    private String baseVersion;

    @Value("${api.speedy.transactions_report.path}")
    private String path;

    @Value("${api.speedy.api_key}")
    private String apiKey;

    private OkHttpClient client = new OkHttpClient();

    @Override
    public Optional<Report> getReport(Date fromDate, Date toDate, Integer merchant, Integer acquirer, String authorization) {
        try {
            HttpUrl.Builder urlBuilder
                    = HttpUrl.parse(baseUrl + baseVersion + path).newBuilder();

            setUriParameters(fromDate, toDate, merchant, acquirer, urlBuilder);

            RequestBody reqbody = RequestBody.create(null, new byte[0]);

            Request request = new Request.Builder()
                    .url(urlBuilder.build().toString())
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Authorization", authorization)
                    .method("POST", reqbody)
                    .build();

            Gson gson = new Gson();
            try (Response response = client.newCall(request).execute()) {
                if (response.code() == HttpStatus.OK.value()) {
                    Report entity = gson.fromJson(response.body().string(), Report.class);
                    return Optional.ofNullable(entity);
                }
                throw new ReportException("Failed to load page response");
            }
        } catch (NullPointerException ex) {
            log.error("Failed to load page response - " + ex.getMessage());
            throw new ReportException("Failed to load page response");
        } catch (Exception ex) {
            log.error("Failed to load page response - " + ex.getMessage());
            throw new ReportException("Failed to load page response");
        }
    }

    private void setUriParameters(Date fromDate, Date toDate, Integer merchant, Integer acquirer, HttpUrl.Builder urlBuilder) {
        if (fromDate != null)
            urlBuilder.addQueryParameter("fromDate", new SimpleDateFormat("yyyy-MM-dd").format(fromDate));
        if (toDate != null)
            urlBuilder.addQueryParameter("toDate", new SimpleDateFormat("yyyy-MM-dd").format(toDate));
        if (acquirer != null)
            urlBuilder.addQueryParameter("acquirer", acquirer.toString());
        if (merchant != null)
            urlBuilder.addQueryParameter("merchant", merchant.toString());
    }
}
