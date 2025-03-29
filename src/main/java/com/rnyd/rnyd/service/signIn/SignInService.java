package com.rnyd.rnyd.service.signIn;

import com.rnyd.rnyd.dto.UserDTO;
import com.rnyd.rnyd.model.UserEntity;
import com.rnyd.rnyd.repository.user.UserRepository;
import com.rnyd.rnyd.service.use_case.SignInUseCase;
import com.rnyd.rnyd.utils.security.JwtUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.Optional;

@Service
public class SignInService implements SignInUseCase {

    private UserRepository userRepository;

    private JwtUtils jwtUtils;

    public SignInService(UserRepository userRepository, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public String signIn(UserDTO userSignInRequest) {
        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(userSignInRequest.getEmail());

        if (userEntityOptional.isPresent() && checkPassword(userEntityOptional.get(), userSignInRequest)) {
            return jwtUtils.generateJWT(userSignInRequest.getEmail(), userEntityOptional.get().getRole().name());
        }

        return null;
    }

    private boolean checkPassword(UserEntity userEntity, UserDTO userSignInRequest) {
        return userEntity.getKeyword().equals(userSignInRequest.getKeyword());
    }
}