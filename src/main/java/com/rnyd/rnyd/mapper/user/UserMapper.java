package com.rnyd.rnyd.mapper.user;

import com.rnyd.rnyd.dto.UserDTO;
import com.rnyd.rnyd.model.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDTO UserEntityToUserDTO(UserEntity entity){
        return new UserDTO(entity.getName(),
                entity.getSurname(),
                entity.getEmail(),
                entity.getKeyword(),
                entity.getBirth_date());
    }

    // UserDTO.builder().name(entity.getName).surname
    public UserEntity UserDTOToUseDTO(UserDTO entity){
        return new UserEntity(entity.getName(),entity.getSurname(),entity.getEmail(),entity.getBirth_date(),entity.getKeyword());
    }

}
