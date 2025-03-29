package com.rnyd.rnyd.service.signUp;

import com.rnyd.rnyd.mapper.user.UserMapper;
import com.rnyd.rnyd.dto.UserDTO;
import com.rnyd.rnyd.model.UserEntity;
import com.rnyd.rnyd.repository.user.UserRepository;
import com.rnyd.rnyd.service.use_case.SignUpUseCase;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SignUpService implements SignUpUseCase {

    private UserRepository userRepository;

    private UserMapper userMapper;

    public SignUpService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDTO> getRegisteredUsers() {
        return userRepository.findAll()
                                 .stream()
                                 .map(userMapper::toDto)
                                 .toList();
    }

    @Override
    public UserDTO register(UserDTO userSignUpRequest) {
        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(userSignUpRequest.getEmail());

        // En el caso de que existe un usuario con ese correo devolver msg error
        if(userEntityOptional.isPresent()){
            return null;
        }

        // Guardamos nuestro usuario en BBDD
        UserEntity userEntity =  userRepository.save(userMapper.toEntity(userSignUpRequest));

        // Devolvemos un CREATED al controller
        return userMapper.toDto(userEntity);
    }
}
