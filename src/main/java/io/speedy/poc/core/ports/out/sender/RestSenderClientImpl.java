package io.speedy.poc.core.ports.out.sender;

import com.google.gson.Gson;
import io.speedy.poc.infra.exceptions.ReportException;
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
    public String post(Map<String, String> parameters, String authorization, String path) {
        try {
            HttpUrl.Builder urlBuilder
                    = HttpUrl.parse(baseUrl + baseVersion + path).newBuilder();

            setParameters(parameters, urlBuilder);

            Request request = getRequest(authorization, urlBuilder);

            Gson gson = new Gson();
            try (Response response = client.newCall(request).execute()) {
                if (response.code() == HttpStatus.OK.value()) {
                    return response.body().string();
                }
                throw new ReportException("Failed to request " + path);
            }
        } catch (NullPointerException ex) {
            log.error("Failed to request " + path + " " + ex.getMessage());
            throw new ReportException("Failed to request " + path);
        } catch (Exception ex) {
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
