package com.rnyd.rnyd.service.signUpService;

import com.rnyd.rnyd.mapper.user.UserMapper;
import com.rnyd.rnyd.dto.UserDTO;
import com.rnyd.rnyd.model.UserEntity;
import com.rnyd.rnyd.repository.user.UserRepository;
import com.rnyd.rnyd.service.use_case.SignUpUseCase;
import com.rnyd.rnyd.utils.constants.Plans;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.rnyd.rnyd.utils.security.PasswordCripter.hashPassword;

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

        userSignUpRequest.setKeyword(hashPassword(userSignUpRequest.getKeyword()));
        userSignUpRequest.setPlan(Plans.NONE);
        // Guardamos nuestro usuario en BBDD
        UserEntity userEntity =  userRepository.save(userMapper.toEntity(userSignUpRequest));

        // Devolvemos un CREATED al controller
        return userMapper.toDto(userEntity);
    }
}
