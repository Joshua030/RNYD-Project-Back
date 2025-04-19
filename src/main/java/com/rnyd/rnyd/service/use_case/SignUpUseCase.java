package com.rnyd.rnyd.service.use_case;

import com.rnyd.rnyd.dto.user.UserDTO;

import java.util.List;

public interface SignUpUseCase {
    List<UserDTO> getRegisteredUsers();

    String register(UserDTO userSignUpRequest);
}
