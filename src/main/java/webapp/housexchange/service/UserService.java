package webapp.housexchange.service;

import webapp.housexchange.dto.property.PropertyDto;
import webapp.housexchange.dto.property.PropertyUpdateDto;
import webapp.housexchange.dto.user.UserCreationDto;
import webapp.housexchange.dto.user.UserDto;
import webapp.housexchange.dto.user.UserOutputDto;
import webapp.housexchange.dto.user.UserUpdateDto;

import java.util.List;

public interface UserService {
//    UserDto createUser(UserCreationDto userCreationDto);

    UserOutputDto getUserById(Long userId);

    List<UserOutputDto> getAllUsers();

    UserDto updateUser(Long userId, UserUpdateDto updatedUser);

    void deleteUser(Long userId);

    List<PropertyDto> getPropertiesByUserId(Long userId);
}
