package com.rnyd.rnyd.controller.registro;

import com.rnyd.rnyd.dto.UserDTO;
import com.rnyd.rnyd.dto.request.UserSignUpRequest;
import com.rnyd.rnyd.dto.response.UserResponse;
import com.rnyd.rnyd.service.signUp.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sign-up") // http://localhost:8080/sign-up
public class RegistroController {

    @Autowired
    private SignUpService signUpService;

    @GetMapping // http://localhost:8080/sign-up GET
    public ResponseEntity<List<UserDTO>> getAllRegisteredUsers(){
        return ResponseEntity.ok(signUpService.getRegisteredUsers());
    }

    @PostMapping("/register") // http://localhost:8080/sign-up/register POST
    public ResponseEntity<UserResponse> register(@RequestBody UserSignUpRequest request){
        return signUpService.register(request);
    }

}
