package com.rnyd.rnyd.mapper.user;

import com.rnyd.rnyd.dto.user.UserDTO;
import com.rnyd.rnyd.mapper.diet.DietMapper;
import com.rnyd.rnyd.mapper.workOut.WorkOutMapper;
import com.rnyd.rnyd.model.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(
        componentModel = "spring",
        uses = {WorkOutMapper.class, DietMapper.class, UserProgressMapper.class, UserMeasurementMapper.class}
)
public interface UserMapper {

    @Mappings({
            @Mapping(source = "progressList", target = "progressList"),
            @Mapping(source = "measurements", target = "measurements")
    })
    UserDTO toDto(UserEntity user);

    @Mappings({
            @Mapping(source = "birth_date", target = "birth_date"),
    })
    UserEntity toEntity(UserDTO userDTO);

    @Mapping(target = "id", ignore = true)
    void updateUserFromDto(UserDTO dto, @MappingTarget UserEntity entity);
}
