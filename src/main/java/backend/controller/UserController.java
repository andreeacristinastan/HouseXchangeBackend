package backend.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import backend.dto.property.PropertyDto;
import backend.dto.user.UserDto;
import backend.dto.user.UserOutputDto;
import backend.dto.user.UserUpdateDto;
import backend.service.PropertyService;
import backend.service.UserService;

import java.util.List;
//import org.springframework.mail.SimpleMailMessage;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", allowPrivateNetwork = "true", allowedHeaders = "*")
public class UserController {
    private UserService userService;
    private PropertyService propertyService;

    //Get user REST API
    @GetMapping("{id}")
    public ResponseEntity<UserOutputDto> getUserById(@PathVariable("id") Long userId) {
        UserOutputDto userDto = userService.getUserById(userId);

        return ResponseEntity.ok(userDto);
    }

    //Get All users REST API
    @GetMapping
    public ResponseEntity<List<UserOutputDto>> getAllUsers() {
        List<UserOutputDto> users = userService.getAllUsers();

        return ResponseEntity.ok(users);
    }

    // Update user REST API
    @PatchMapping("{id}")
    public ResponseEntity<UserOutputDto> updateUser(@PathVariable("id") Long userId,
                                              @RequestBody UserUpdateDto userUpdateDto) {
        UserOutputDto userOutputDto = userService.updateUser(userId, userUpdateDto);

        return ResponseEntity.ok(userOutputDto);
    }

    //Delete user REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId) {
        userService.deleteUser(userId);

        return ResponseEntity.ok("User deleted successfully");
    }

    //Get properties by user REST API
    @GetMapping("{id}/properties")
    public ResponseEntity<List<PropertyDto>> getPropertiesByUserId(@PathVariable("id") Long userId) {
        List<PropertyDto> properties = userService.getPropertiesByUserId(userId);
        return ResponseEntity.ok(properties);
    }

}
