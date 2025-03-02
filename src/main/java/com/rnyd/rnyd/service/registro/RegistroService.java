package com.rnyd.rnyd.service.registro;

import com.rnyd.rnyd.dto.request.UserSignUpRequest;
import com.rnyd.rnyd.dto.response.UserResponse;
import com.rnyd.rnyd.dto.response.UserSignUpResponse;
import com.rnyd.rnyd.exceptionHandler.exceptions.RegisterException;
import com.rnyd.rnyd.mapper.user.UserMapper;
import com.rnyd.rnyd.dto.UserDTO;
import com.rnyd.rnyd.model.UserEntity;
import com.rnyd.rnyd.repository.registro.RegistroRepository;
import com.rnyd.rnyd.service.use_case.RegistroUseCase;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.rnyd.rnyd.utils.constants.ExceptionMessages.USER_EMAIL_ALREADY_EXISTS;

@Service
public class RegistroService implements RegistroUseCase {

    @Autowired
    private RegistroRepository registroRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserDTO> getRegisteredUsers() {
        return registroRepository.findAll()
                                 .stream()
                                 .map(userMapper::UserEntityToUserDTO)
                                 .toList();
    }

    @Override
    public ResponseEntity<UserResponse> register(UserSignUpRequest userSignUpRequest) {

        Optional<UserEntity> userEntityOptional = registroRepository.findByEmail(userSignUpRequest.getEmail());

        // En el caso de que existe un usuario con ese correo devolver msg error
        if(userEntityOptional.isPresent()){
            // TODO Emplear lanzamiento de error con GlobalExceptionHandler
            return ResponseEntity.badRequest().body(new UserResponse(USER_EMAIL_ALREADY_EXISTS.getMessage()));
        }


        return null;
    }


}
