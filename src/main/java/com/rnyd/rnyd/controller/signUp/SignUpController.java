package com.rnyd.rnyd.controller.signUp;

import com.rnyd.rnyd.dto.UserDTO;
import com.rnyd.rnyd.service.signUpService.SignUpService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/signup") // http://localhost:8080/sign-up
public class SignUpController {

    private final SignUpService signUpService;

    public SignUpController(SignUpService signUpService) {
        this.signUpService = signUpService;
    }

    @GetMapping // http://localhost:8080/sign-up GET
    public ResponseEntity<List<UserDTO>> getAllRegisteredUsers(){
        return ResponseEntity.ok(signUpService.getRegisteredUsers());
    }

    @PostMapping("/register") // http://localhost:8080/signup/register POST
    public ResponseEntity<String> register(@RequestBody UserDTO request){
        String token = signUpService.register(request);
        if(token != null)
            return ResponseEntity.ok(token);
        else
            return ResponseEntity.badRequest().body(null);
    }

}
