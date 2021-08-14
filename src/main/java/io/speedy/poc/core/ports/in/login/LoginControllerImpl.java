package io.speedy.poc.core.ports.in.login;

import io.speedy.poc.core.usecase.LoginUseCase;
import io.speedy.poc.core.usecase.transferobject.AccessTokenTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/v1/merchant/user")
@CrossOrigin
public class LoginControllerImpl implements LoginController {
    @Autowired
    private LoginUseCase loginUseCaseImpl;

    @PostMapping(path = "login")
    @Override
    public ResponseEntity<AccessTokenTO> login(@Valid @NotNull @NotBlank @RequestParam("email") String email,
                                               @Valid @NotNull @NotBlank @RequestParam("password") String password) {
        AccessTokenTO accessToken = loginUseCaseImpl.validateAndReturnAccessToken(email, password);
        return ResponseEntity.ok(accessToken);
    }
}
