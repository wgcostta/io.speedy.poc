package io.speedy.poc.core.usecase.login;

import com.google.gson.Gson;
import io.speedy.poc.core.ports.in.login.transferobject.AccessTokenTO;
import io.speedy.poc.core.ports.out.sender.RestSenderClient;
import io.speedy.poc.core.ports.out.sender.transferobject.ResponseTO;
import io.speedy.poc.core.usecase.login.transferobject.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class LoginUseCaseImpl implements LoginUseCase {

    @Autowired
    RestSenderClient restSenderClient;

    @Value("${api.speedy.login.path}")
    private String path;

    @Override
    public AccessTokenTO validateAndReturnAccessToken(String email, String password) {
        Map<String, String> parameters = new HashMap<>();

        parameters.put("email", email);
        parameters.put("password", password);
        ResponseTO response = restSenderClient.post(parameters, "", path);

        Gson gson = new Gson();
        Optional<AccessToken> accessTokenOptional = Optional.ofNullable(gson.fromJson(response.getBody(), AccessToken.class));

        return accessTokenOptional.map(accessToken -> new AccessTokenTO(accessToken.getToken(), accessToken.getStatus())).orElseGet(AccessTokenTO::new);
    }
}
