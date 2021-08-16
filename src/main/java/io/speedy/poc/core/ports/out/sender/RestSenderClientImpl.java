package io.speedy.poc.core.ports.out.sender;

import com.google.gson.Gson;
import io.speedy.poc.core.ports.out.sender.transferobject.ResponseTO;
import io.speedy.poc.infra.exceptions.ReportException;
import io.speedy.poc.infra.exceptions.UnauthorizedUserException;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class RestSenderClientImpl implements RestSenderClient {
    @Value("${api.speedy.base_url}")
    private String baseUrl;

    @Value("${api.speedy.base_version}")
    private String baseVersion;

    @Value("${api.speedy.api_key}")
    private String apiKey;

    private OkHttpClient client = new OkHttpClient();

    private Request getRequest(String authorization, HttpUrl.Builder urlBuilder) {
        RequestBody reqbody = RequestBody.create(null, new byte[0]);

        return new Request.Builder()
                .url(urlBuilder.build().toString())
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", authorization)
                .method("POST", reqbody)
                .build();
    }

    @Override
    public ResponseTO post(Map<String, String> parameters, String authorization, String path) {
        String body = "";
        Integer statusCode = 0;
        try {
            HttpUrl.Builder urlBuilder
                    = HttpUrl.parse(baseUrl + baseVersion + path).newBuilder();

            setParameters(parameters, urlBuilder);

            Request request = getRequest(authorization, urlBuilder);

            Gson gson = new Gson();
            try (Response response = client.newCall(request).execute()) {
                body = response.body().string();
                statusCode = response.code();
                if (statusCode.equals(HttpStatus.OK.value())) {
                    return new ResponseTO(response.code(), body);
                }
                throw new ReportException("Failed to request " + path);
            }
        } catch (NullPointerException ex) {
            log.error("Failed to request " + path + " " + ex.getMessage());
            throw new ReportException("Failed to request " + path);
        } catch (Exception ex) {
            if (path.contains("login") && statusCode.equals(500)) {
                if (body.contains("\"status\":\"DECLINED\""))
                    throw new UnauthorizedUserException("Merchant User credentials is not valid");
            }
            log.error("Failed to request " + path + " " + ex.getMessage());
            throw new ReportException("Failed to request " + path);
        }
    }

    private void setParameters(Map<String, String> parameters, HttpUrl.Builder urlBuilder) {
        parameters.forEach((key, value) -> {
            urlBuilder.addQueryParameter(key, value);
        });
        urlBuilder.addQueryParameter("apiKey", apiKey);
    }
}
