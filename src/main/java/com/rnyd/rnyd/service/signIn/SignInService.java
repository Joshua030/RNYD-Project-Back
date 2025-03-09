package com.rnyd.rnyd.service.signIn;

import com.rnyd.rnyd.dto.request.UserSignInRequest;
import com.rnyd.rnyd.dto.response.UserResponse;
import com.rnyd.rnyd.model.UserEntity;
import com.rnyd.rnyd.repository.user.UserRepository;
import com.rnyd.rnyd.service.use_case.SignInUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public class SignInService implements SignInUseCase {

    @Autowired
    private UserRepository userRepository;


    @Override
    public String generateJWT(String user, String role) {
        return "";
    }

    @Override
    public String validateJWT(String token) {
        return "";
    }

    @Override
    public ResponseEntity<String> signIn(UserSignInRequest userSignInRequest) {

        // Validar que existe en BBDD y la contraseña coincide
        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(userSignInRequest.getEmail());

        if(userEntityOptional.isPresent() && checkPassword(userEntityOptional.get(), userSignInRequest)){
            // Generar Token por usuario y role (por ahora todos user
            return ResponseEntity.ok(generateJWT(userSignInRequest.getEmail(),"user"));
        }


        // Devolver token de inicio de sesion junto al response

        // Devolver mensaje de error si no coinciden


        return null;
    }

    private boolean checkPassword(UserEntity userEntity, UserSignInRequest userSignInRequest){
        return userEntity.getKeyword().equals(userSignInRequest.getKeyword());
    }

}
