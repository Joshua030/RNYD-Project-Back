package com.rnyd.rnyd.service.use_case;

import com.rnyd.rnyd.dto.UserDTO;

import java.util.List;

public interface UserUseCase {
    String deleteUser(String email);
    String modifyUser(UserDTO userDTO);
    List<UserDTO> getAllUsers();
    UserDTO getUserByEmail(String email);
    Boolean checkAdminRole(String email);
}
