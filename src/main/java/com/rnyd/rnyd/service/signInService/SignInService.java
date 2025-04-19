package com.rnyd.rnyd.service.signInService;

import com.rnyd.rnyd.dto.user.UserDTO;
import com.rnyd.rnyd.model.UserEntity;
import com.rnyd.rnyd.repository.user.UserRepository;
import com.rnyd.rnyd.service.jwtService.JwtService;
import com.rnyd.rnyd.service.use_case.SignInUseCase;
import com.rnyd.rnyd.utils.security.PasswordCripter;
import org.springframework.stereotype.Service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class SignInService implements SignInUseCase {

    private static final Logger logger = LoggerFactory.getLogger(SignInService.class);
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordCripter passwordCripter;

    public SignInService(UserRepository userRepository, JwtService jwtService, PasswordCripter passwordCripter) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordCripter = passwordCripter;
    }

    @Override
    public String signIn(UserDTO userSignInRequest) {
        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(userSignInRequest.getEmail());

        if(userEntityOptional.isPresent()){
            UserEntity userEntity = userEntityOptional.get();
            String decryptedPassword = passwordCripter.decryptPassword(userEntity.getKeyword());

            logger.info("Contraseña desencriptada: {}", decryptedPassword);

            if(decryptedPassword.equals(userSignInRequest.getKeyword())){
                return jwtService.generateToken(userSignInRequest.getEmail(), userEntity.getRole().name());
            }
        }
        return null;
    }

    @Override
    public String registerSignIn(UserEntity userEntity) {
        return jwtService.generateToken(userEntity.getEmail(), userEntity.getRole().name());
    }
}