package io.speedy.poc.core.usecase;

import io.speedy.poc.core.ports.out.admin.RestSenderPortLogin;
import io.speedy.poc.core.usecase.transferobject.AccessTokenTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginUseCaseImpl implements LoginUseCase {

    @Autowired
    RestSenderPortLogin restSenderPortLogin;

    @Override
    public AccessTokenTO validateAndReturnAccessToken(String email, String password) {
        String token = restSenderPortLogin.getAccessToken(email, password);
        return new AccessTokenTO(token);
    }
}
