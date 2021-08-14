package io.speedy.poc.core.ports.out.admin;

import com.google.gson.Gson;
import io.speedy.poc.core.ports.out.transferobject.AccessToken;
import io.speedy.poc.infra.exceptions.UnauthorizedUserException;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RestSenderPortLoginImpl implements RestSenderPortLogin {

    @Value("${api.speedy.base_url}")
    private String baseUrl;

    @Value("${api.speedy.base_version}")
    private String baseVersion;

    @Value("${api.speedy.login.path}")
    private String pathLogin;

    @Value("${api.speedy.api_key}")
    private String apiKey;

    private OkHttpClient client = new OkHttpClient();

    @Override
    public String getAccessToken(String email, String password) {
        try {
            HttpUrl.Builder urlBuilder
                    = HttpUrl.parse(baseUrl + baseVersion + pathLogin).newBuilder();

            urlBuilder.addQueryParameter("email", email);
            urlBuilder.addQueryParameter("password", password);
            urlBuilder.addQueryParameter("apiKey", apiKey);

            RequestBody reqbody = RequestBody.create(null, new byte[0]);

            Request request = new Request.Builder()
                    .url(urlBuilder.build().toString())
                    .addHeader("Content-Type", "application/json")
                    .method("POST", reqbody)
                    .build();

            Gson gson = new Gson();
            try (Response response = client.newCall(request).execute()) {
                if (response.code() == HttpStatus.OK.value()) {
                    AccessToken entity = gson.fromJson(response.body().string(), AccessToken.class);
                    return entity.getToken();
                }
                throw new UnauthorizedUserException("Login failed");
            }
        } catch (NullPointerException ex) {
            log.error("Error retrieving token" + ex.getMessage());
            throw new UnauthorizedUserException("Unauthorized User");
        } catch (Exception ex) {
            log.error("Error retrieving token" + ex.getMessage());
            throw new UnauthorizedUserException("Unauthorized User");
        }
    }


}
