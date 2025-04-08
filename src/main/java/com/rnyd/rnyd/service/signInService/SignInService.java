package com.rnyd.rnyd.service.signInService;

import com.rnyd.rnyd.dto.UserDTO;
import com.rnyd.rnyd.model.UserEntity;
import com.rnyd.rnyd.repository.user.UserRepository;
import com.rnyd.rnyd.service.jwtService.JwtService;
import com.rnyd.rnyd.service.use_case.SignInUseCase;
import org.springframework.stereotype.Service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.rnyd.rnyd.utils.security.PasswordCripter.decryptPassword;

@Service
public class SignInService implements SignInUseCase {

    private UserRepository userRepository;
    private JwtService jwtService;

    public SignInService(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @Override
    public String signIn(UserDTO userSignInRequest) {
        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(userSignInRequest.getEmail());

        if (userEntityOptional.isPresent() && checkPassword(userEntityOptional.get().getKeyword(), userSignInRequest.getKeyword())) {
            return jwtService.generateToken(userSignInRequest.getEmail(), userEntityOptional.get().getRole().name());
        }

        return null;
    }*/

    @Override
    public String signIn(UserDTO userSignInRequest) {
        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(userSignInRequest.getEmail());

        if(userEntityOptional.isPresent()){
            UserEntity userEntity = userEntityOptional.get();
            String decryptedPassword = decryptPassword(userEntity.getKeyword());

            logger.info("Contraseña desencriptada: {}", decryptedPassword);

            if(decryptedPassword.equals(userSignInRequest.getKeyword())){
                return jwtService.generateToken(userSignInRequest.getEmail(), userEntity.getRole().name());
            }
        }
        return null;
    }
}