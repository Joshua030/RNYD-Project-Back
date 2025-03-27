package com.rnyd.rnyd.service.use_case;

import com.rnyd.rnyd.dto.UserDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SignUpUseCase {
    List<UserDTO> getRegisteredUsers();

    ResponseEntity<UserDTO> register(UserDTO userSignUpRequest);
}
