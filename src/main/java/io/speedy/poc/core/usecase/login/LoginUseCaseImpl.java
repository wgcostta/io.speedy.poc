package io.speedy.poc.core.usecase.login;

import io.speedy.poc.core.ports.out.login.RestSenderPortLogin;

import io.speedy.poc.core.ports.out.login.transferobject.AccessToken;
import io.speedy.poc.core.usecase.login.transferobject.AccessTokenTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginUseCaseImpl implements LoginUseCase {

    @Autowired
    RestSenderPortLogin restSenderPortLogin;

    @Override
    public AccessTokenTO validateAndReturnAccessToken(String email, String password) {
         Optional<AccessToken> accessTokenOptional = restSenderPortLogin.getAccessToken(email, password);
         if(accessTokenOptional.isPresent())
             return new AccessTokenTO(accessTokenOptional.get().getToken(), accessTokenOptional.get().getStatus());
         return new AccessTokenTO();
    }
}
