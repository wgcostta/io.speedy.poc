package io.speedy.poc.core.usecase;

import io.speedy.poc.core.usecase.transferobject.AccessTokenTO;

public interface LoginUseCase {
    AccessTokenTO validateAndReturnAccessToken(String email, String password);
}
