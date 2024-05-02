package backend.controller.auth;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import backend.dto.user.UserCreationDto;
import backend.dto.user.UserDto;
import backend.service.impl.RegistrationImpl;
import backend.utils.JsonHelper;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class RegistrationController {
    private RegistrationImpl registration;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserCreationDto userCreationDTO) {
            UserDto savedUser = registration.createUser(userCreationDTO);
            return ResponseEntity.ok(new JsonHelper("success:", "User registered successfully"));
    }
}
