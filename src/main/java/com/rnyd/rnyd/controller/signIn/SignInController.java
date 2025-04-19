package com.rnyd.rnyd.controller.signIn;

import com.rnyd.rnyd.dto.user.UserDTO;
import com.rnyd.rnyd.service.jwtService.JwtService;
import com.rnyd.rnyd.service.use_case.SignInUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.rnyd.rnyd.utils.constants.Variables.*;

@RestController
@RequestMapping("/auth")
public class SignInController {

    private final SignInUseCase signInUseCase;

    private final JwtService jwtService;

    public SignInController(SignInUseCase signInUseCase, JwtService jwtService) {
        this.signInUseCase = signInUseCase;
        this.jwtService = jwtService;
    }

    @PostMapping("/signin")
    public ResponseEntity<String> signIn(@RequestBody UserDTO userSignInRequest) {
        String token = signInUseCase.signIn(userSignInRequest);
        if(token != null)
            return ResponseEntity.ok(token);
        else
            return new ResponseEntity<>(WRONG_LOGIN, HttpStatus.UNAUTHORIZED);
    }

    // Hablar con front por si quiere ser por header o param
    @PostMapping("/validate-token")
    public ResponseEntity<String> validateToken(@RequestBody String token) {
        if (jwtService.isTokenExpired(token)) {
            return ResponseEntity.status(401).body(INVALID_TOKEN);
        } else
            return ResponseEntity.ok(VALID_TOKEN);

    }
}