package com.rnyd.rnyd.service.use_case;

import com.rnyd.rnyd.dto.UserDTO;
import com.rnyd.rnyd.dto.request.UserSignUpRequest;
import com.rnyd.rnyd.dto.response.UserResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SignUpUseCase {
    List<UserDTO> getRegisteredUsers();

    ResponseEntity<UserResponse> register(UserSignUpRequest userSignUpRequest);
}
