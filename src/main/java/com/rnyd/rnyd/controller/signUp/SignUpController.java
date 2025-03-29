package com.rnyd.rnyd.controller.signUp;

import com.rnyd.rnyd.dto.UserDTO;
import com.rnyd.rnyd.service.signUp.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.rnyd.rnyd.utils.constants.Variables.USER_EMAIL_ALREADY_EXISTS;

@RestController
@RequestMapping("/signup") // http://localhost:8080/sign-up
public class SignUpController {

    private SignUpService signUpService;

    public SignUpController(SignUpService signUpService) {
        this.signUpService = signUpService;
    }

    @GetMapping // http://localhost:8080/sign-up GET
    public ResponseEntity<List<UserDTO>> getAllRegisteredUsers(){
        return ResponseEntity.ok(signUpService.getRegisteredUsers());
    }

    @PostMapping("/register") // http://localhost:8080/signup/register POST
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO request){
        UserDTO userDTO = signUpService.register(request);
        if(userDTO != null)
            return ResponseEntity.ok(userDTO);
        else
            return ResponseEntity.badRequest().body(null);
    }

}
