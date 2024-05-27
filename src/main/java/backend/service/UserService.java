package backend.service;

import backend.dto.property.PropertyDto;
import backend.dto.user.UserDto;
import backend.dto.user.UserOutputDto;
import backend.dto.user.UserUpdateDto;

import java.util.List;

public interface UserService {
//    UserDto createUser(UserCreationDto userCreationDto);

    UserOutputDto getUserById(Long userId);

    List<UserOutputDto> getAllUsers();

    UserOutputDto updateUser(Long userId, UserUpdateDto updatedUser);

    void deleteUser(Long userId);

    List<PropertyDto> getPropertiesByUserId(Long userId);
}
