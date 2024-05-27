package backend.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import backend.dto.property.PropertyDto;
import backend.dto.user.UserDto;
import backend.dto.user.UserOutputDto;
import backend.dto.user.UserUpdateDto;
import backend.entity.User;
import backend.exceptions.DatabaseException;
import backend.exceptions.ResourceNotFoundException;
import backend.mapper.PropertyMapper;
import backend.mapper.UserMapper;
import backend.repository.PropertyRepository;
import backend.repository.UserRepository;
import backend.security.AuthUtil;
import backend.service.UserService;
import backend.utils.CheckPermissionsHelper;

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
    public UserOutputDto updateUser(Long userId, UserUpdateDto updatedUser) {

        User user;
        try {
            user = userRepository.findById(userId).orElseThrow(
                    () -> new ResourceNotFoundException("User is not exists with given id: " + userId));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

        checkPermissionsHelper.checkAuth(user.getUsername(), authUtil);
        if(updatedUser.getEmail().length() != 0) {
            user.setEmail(updatedUser.getEmail());
        }

        if(updatedUser.getFirstName().length() != 0) {
            user.setFirstName(updatedUser.getFirstName());
        }

        if(updatedUser.getLastName().length() != 0) {
            user.setLastName(updatedUser.getLastName());
        }

        if(updatedUser.getLanguage().length() != 0) {
            user.setLanguage(updatedUser.getLanguage());
        }

        if(updatedUser.getPhoneNumber() != null) {
            user.setPhoneNumber(updatedUser.getPhoneNumber());
        }

        if(updatedUser.getPrefixNumber() != null) {
            user.setPrefixNumber(updatedUser.getPrefixNumber());
        }

        User updatedUserObj;
        try {
            updatedUserObj = userRepository.save(user);
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

        return UserMapper.mapToUserOutputDto(updatedUserObj);
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
