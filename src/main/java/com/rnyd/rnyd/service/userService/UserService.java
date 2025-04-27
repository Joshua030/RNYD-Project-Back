package com.rnyd.rnyd.service.userService;

import com.rnyd.rnyd.dto.user.UserDTO;
import com.rnyd.rnyd.mapper.user.UserMapper;
import com.rnyd.rnyd.model.UserEntity;
import com.rnyd.rnyd.repository.user.UserRepository;
import com.rnyd.rnyd.service.use_case.UserUseCase;
import com.rnyd.rnyd.utils.NullAwareBeanUtils;
import com.rnyd.rnyd.utils.constants.Roles;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.rnyd.rnyd.utils.constants.Variables.USER_DELETED;
import static com.rnyd.rnyd.utils.constants.Variables.USER_UPDATED;

@Service
public class UserService implements UserUseCase {

    private final UserMapper userMapper;
    private final UserRepository userRepository;


    public UserService(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @Override
    public String deleteUser(String email) {
        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(email);

        if(userEntityOptional.isEmpty())
            return null;

        userRepository.delete(userEntityOptional.get());

        return USER_DELETED;
    }

    @Override
    public String modifyUser(String email, UserDTO userDTO) {
        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(email);

        if (userEntityOptional.isEmpty())
            return null;

        UserEntity existingUser = userEntityOptional.get();

        NullAwareBeanUtils.copyNonNullProperties(userDTO, existingUser);

        userRepository.save(existingUser);

        return USER_UPDATED;
    }


    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toDto).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public UserDTO getUserByEmail(String email) {
        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(email);

        if (userEntityOptional.isPresent()) {
            UserEntity user = userEntityOptional.get();

            // Forzamos carga de los LOBs si están en diet y workout
            if (user.getDiet() != null) {
                user.getDiet().getDietPdf(); // fuerza la carga
            }

            if (user.getWorkout() != null) {
                user.getWorkout().getWorkoutPdf(); // fuerza la carga
            }

            return userMapper.toDto(user);
        }

        return null;
    }


    @Override
    public Boolean checkAdminRole(String email) {
        UserDTO userDTO = getUserByEmail(email);
        if(userDTO == null)
            return null;

        return userDTO.getRole() == Roles.ADMIN;
    }
}
