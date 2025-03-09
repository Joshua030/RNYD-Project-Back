package com.rnyd.rnyd.service.use_case;

import com.rnyd.rnyd.dto.request.UserSignInRequest;
import com.rnyd.rnyd.dto.response.UserResponse;
import org.springframework.http.ResponseEntity;

public interface SignInUseCase {
    String generateJWT(String user, String role);

    String validateJWT(String token);

    ResponseEntity<String> signIn(UserSignInRequest userSignInRequest);
}
