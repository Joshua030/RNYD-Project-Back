package com.rnyd.rnyd.service.use_case;

import com.rnyd.rnyd.dto.UserDTO;
import org.springframework.http.ResponseEntity;

public interface SignInUseCase {
    String signIn(UserDTO userSignInRequest);
}
