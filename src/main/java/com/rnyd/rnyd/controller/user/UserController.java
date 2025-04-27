package com.rnyd.rnyd.controller.user;

import com.rnyd.rnyd.dto.user.UserDTO;
import com.rnyd.rnyd.service.userService.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email){
        UserDTO userDTO = userService.getUserByEmail(email);

        if(userDTO != null)
            return new ResponseEntity<>(userDTO, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/{email}")
    public ResponseEntity<String> modifyUser(@PathVariable String email, @RequestBody UserDTO userDTO){
        String response = userService.modifyUser(email, userDTO);

        if(response != null)
            return new ResponseEntity<>(response, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<String> deleteUser(@PathVariable String email){
        String response = userService.deleteUser(email);

        if(response != null)
            return new ResponseEntity<>(response, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/check-admin/{email}")
    public ResponseEntity<Boolean> checkAdminRole(@PathVariable String email){
        return new ResponseEntity<>(userService.checkAdminRole(email), HttpStatus.OK);
    }

}
