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

        if (userEntityOptional.isEmpty())
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

            // Force load all diet PDFs
            if (user.getDiets() != null && !user.getDiets().isEmpty()) {
                user.getDiets().forEach(d -> {
                    if (d.getDietPdf() != null) {
                        byte[] preview = d.getDietPdf(); // force fetch
                    }
                });
            }

            // Force load all workout PDFs
            if (user.getWorkouts() != null && !user.getWorkouts().isEmpty()) {
                user.getWorkouts().forEach(w -> {
                    if (w.getWorkoutPdf() != null) {
                        byte[] preview = w.getWorkoutPdf(); // force fetch
                    }
                });
            }

            return userMapper.toDto(user);
        }

        return null;
    }

    @Override
    public Boolean checkAdminRole(String email) {
        UserDTO userDTO = getUserByEmail(email);
        if (userDTO == null)
            return null;

        return userDTO.getRole() == Roles.ADMIN;
    }
}
