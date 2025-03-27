package com.rnyd.rnyd.service.signUp;

import com.rnyd.rnyd.exceptionHandler.exceptions.RegisterException;
import com.rnyd.rnyd.mapper.user.UserMapper;
import com.rnyd.rnyd.dto.UserDTO;
import com.rnyd.rnyd.model.UserEntity;
import com.rnyd.rnyd.repository.user.UserRepository;
import com.rnyd.rnyd.service.use_case.SignUpUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.rnyd.rnyd.utils.constants.ExceptionMessages.USER_EMAIL_ALREADY_EXISTS;

@Service
public class SignUpService implements SignUpUseCase {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserDTO> getRegisteredUsers() {
        return userRepository.findAll()
                                 .stream()
                                 .map(userMapper::toDto)
                                 .toList();
    }

    @Override
    public ResponseEntity<UserDTO> register(UserDTO userSignUpRequest) {
        // TODO VALIDAR CAMPOS
        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(userSignUpRequest.getEmail());

        // En el caso de que existe un usuario con ese correo devolver msg error
        // TODO NO ES CORRECTO
        if(userEntityOptional.isPresent()){
            throw new RegisterException(USER_EMAIL_ALREADY_EXISTS.getMessage());
        }

        // Guardamos nuestro usuario en BBDD
        userRepository.save(userMapper.toEntity(userSignUpRequest));

        // Devolvemos un CREATED al controller
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
