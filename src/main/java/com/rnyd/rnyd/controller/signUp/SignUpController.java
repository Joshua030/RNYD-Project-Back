package com.rnyd.rnyd.controller.signUp;

import com.rnyd.rnyd.dto.UserDTO;
import com.rnyd.rnyd.service.signUp.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/signup") // http://localhost:8080/sign-up
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @GetMapping // http://localhost:8080/sign-up GET
    public ResponseEntity<List<UserDTO>> getAllRegisteredUsers(){
        return ResponseEntity.ok(signUpService.getRegisteredUsers());
    }

    @PostMapping("/register") // http://localhost:8080/signup/register POST
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO request){
        return signUpService.register(request);
    }

}
