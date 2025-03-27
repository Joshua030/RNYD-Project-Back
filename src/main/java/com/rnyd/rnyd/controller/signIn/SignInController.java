package com.rnyd.rnyd.controller.signIn;

import com.rnyd.rnyd.dto.UserDTO;
import com.rnyd.rnyd.service.jwt.JwtService;
import com.rnyd.rnyd.service.use_case.SignInUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class SignInController {

    @Autowired
    private SignInUseCase signInUseCase;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/signin")
    public ResponseEntity<String> signIn(@RequestBody UserDTO userSignInRequest) {
        return signInUseCase.signIn(userSignInRequest);
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