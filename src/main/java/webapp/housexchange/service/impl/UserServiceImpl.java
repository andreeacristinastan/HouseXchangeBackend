package webapp.housexchange.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import webapp.housexchange.constants.Role;
import webapp.housexchange.dto.property.PropertyDto;
import webapp.housexchange.dto.property.PropertyUpdateDto;
import webapp.housexchange.dto.user.UserCreationDto;
import webapp.housexchange.dto.user.UserDto;
import webapp.housexchange.dto.user.UserOutputDto;
import webapp.housexchange.dto.user.UserUpdateDto;
import webapp.housexchange.entity.Property;
import webapp.housexchange.entity.User;
import webapp.housexchange.exceptions.DatabaseException;
import webapp.housexchange.exceptions.ResourceNotFoundException;
import webapp.housexchange.mapper.PropertyMapper;
import webapp.housexchange.mapper.UserMapper;
import webapp.housexchange.repository.PropertyRepository;
import webapp.housexchange.repository.UserRepository;
import webapp.housexchange.security.AuthUtil;
import webapp.housexchange.service.UserService;
import webapp.housexchange.utils.CheckPermissionsHelper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PropertyRepository propertyRepository;
    private AuthUtil authUtil;
    private CheckPermissionsHelper checkPermissionsHelper;

//    @Override
//    public UserDto createUser(UserCreationDto userCreationDTO) {
//        try {
//            Role role = Role.valueOf(userCreationDTO.getRole());
//        } catch (IllegalArgumentException e) {
//            throw new IllegalArgumentException("Invalid role: " + userCreationDTO.getRole() + " you can choose between GUEST and HOST");
//        }
//
//        User user = UserMapper.mapToUser(userCreationDTO);
//        User savedUser;
//
//        try {
//            savedUser = userRepository.save(user);
//        } catch (DataAccessException exception) {
//            throw new DatabaseException("Exception occurred while accessing the database", exception);
//        }
//
//        return UserMapper.mapToUserDto(savedUser);
//    }

    @Override
    public UserOutputDto getUserById(Long userId) {
        User user;
        try {
            user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User is not exists with given id: " + userId));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }
        return UserMapper.mapToUserOutputDto(user);
    }

    @Override
    public List<UserOutputDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserMapper::mapToUserOutputDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(Long userId, UserUpdateDto updatedUser) {

        User user;
        try {
            user = userRepository.findById(userId).orElseThrow(
                    () -> new ResourceNotFoundException("User is not exists with given id: " + userId));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

        checkPermissionsHelper.checkAuth(user.getEmail(), authUtil);

        user.setEmail(updatedUser.getEmail());
        user.setPassword(updatedUser.getPassword());
        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setLanguage(updatedUser.getLanguage());
        user.setPhoneNumber(updatedUser.getPhoneNumber());
        user.setRole(updatedUser.getRole());

        User updatedUserObj;
        try {
            updatedUserObj = userRepository.save(user);
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }
        return UserMapper.mapToUserDto(updatedUserObj);
    }

    @Override
    public void deleteUser(Long userId) {
        User user;
        try {
            user = userRepository.findById(userId).orElseThrow(
                    () -> new ResourceNotFoundException("User is not exists with given id: " + userId));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

        checkPermissionsHelper.checkAuth(user.getEmail(), authUtil);

        userRepository.deleteById(userId);
    }

    @Override
    public List<PropertyDto> getPropertiesByUserId(Long userId) {
        User user;

        try {
            user = userRepository.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }
        return user.getProperties().stream()
                .map(PropertyMapper::mapToPropertyDto)
                .collect(Collectors.toList());
    }



}
