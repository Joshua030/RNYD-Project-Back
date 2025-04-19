package com.rnyd.rnyd.service.use_case;

import com.rnyd.rnyd.dto.user.UserDTO;
import com.rnyd.rnyd.model.UserEntity;

public interface SignInUseCase {
    String signIn(UserDTO userSignInRequest);
    String registerSignIn(UserEntity userEntity);
}
