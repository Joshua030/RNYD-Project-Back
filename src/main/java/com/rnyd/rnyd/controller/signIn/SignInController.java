package com.rnyd.rnyd.controller.signIn;

import com.rnyd.rnyd.dto.UserDTO;
import com.rnyd.rnyd.service.jwtService.JwtService;
import com.rnyd.rnyd.service.use_case.SignInUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class SignInController {

    private SignInUseCase signInUseCase;

    private JwtService jwtService;

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
            return new ResponseEntity<>("Email o contraseña inválida.", HttpStatus.UNAUTHORIZED);
    }

    // Hablar con front por si quiere ser por header o param
    @PostMapping("/validate-token")
    public ResponseEntity<String> validateToken(@RequestParam String token) {
        if (jwtService.isTokenExpired(token)) {
            return ResponseEntity.status(401).body("Token inválido o expirado.");
        } else {
            return ResponseEntity.ok("Token válido");
        }
    }
}