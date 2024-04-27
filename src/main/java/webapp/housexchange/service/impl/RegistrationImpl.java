package webapp.housexchange.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import webapp.housexchange.constants.Role;
import webapp.housexchange.dto.user.UserCreationDto;
import webapp.housexchange.dto.user.UserDto;
import webapp.housexchange.entity.User;
import webapp.housexchange.exceptions.DatabaseException;
import webapp.housexchange.exceptions.InvalidRoleException;
import webapp.housexchange.mapper.UserMapper;
import webapp.housexchange.repository.UserRepository;

import java.util.Arrays;

@Service
@AllArgsConstructor
public class RegistrationImpl {
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    public UserDto createUser(UserCreationDto userCreationDTO) {
        try {
            Role role = Role.valueOf(userCreationDTO.getRole());
        } catch (IllegalArgumentException exception) {
            throw new InvalidRoleException("Invalid role: " + userCreationDTO.getRole() + ". You can choose between " + Arrays.toString(Role.values()));
        }

        User user = UserMapper.mapToUser(userCreationDTO);
        user.setPassword(passwordEncoder.encode(userCreationDTO.getPassword()));

        User savedUser;

        try {
            savedUser = userRepository.save(user);
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

        return UserMapper.mapToUserDto(savedUser);
    }
}
