package io.speedy.poc.core.ports.in.login;

import io.speedy.poc.core.usecase.login.LoginUseCase;
import io.speedy.poc.core.ports.in.login.transferobject.AccessTokenTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/merchant/user")
public class LoginControllerImpl implements LoginController {
    @Autowired
    private LoginUseCase loginUseCaseImpl;

    @PostMapping(path = "login")
    @Override
    public ResponseEntity<AccessTokenTO> login(@RequestParam("email") String email,
                                               @RequestParam("password") String password) {
        AccessTokenTO accessToken = loginUseCaseImpl.validateAndReturnAccessToken(email, password);
        return ResponseEntity.ok(accessToken);
    }
}
