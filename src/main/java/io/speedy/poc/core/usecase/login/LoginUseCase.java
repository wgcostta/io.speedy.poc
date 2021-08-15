package io.speedy.poc.core.usecase.login;

import io.speedy.poc.core.usecase.login.transferobject.AccessTokenTO;

public interface LoginUseCase {
    AccessTokenTO validateAndReturnAccessToken(String email, String password);
}
