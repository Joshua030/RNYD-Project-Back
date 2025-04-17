package com.rnyd.rnyd.service.use_case;

import com.rnyd.rnyd.dto.UserDTO;
import com.rnyd.rnyd.model.UserEntity;
import org.springframework.http.ResponseEntity;

public interface SignInUseCase {
    String signIn(UserDTO userSignInRequest);
    String registerSignIn(UserEntity userEntity);
}
