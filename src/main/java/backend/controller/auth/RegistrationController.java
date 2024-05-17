package backend.controller.auth;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import backend.dto.user.UserCreationDto;
import backend.dto.user.UserDto;
import backend.service.impl.RegistrationImpl;
import backend.utils.JsonHelper;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173", allowPrivateNetwork = "true", allowedHeaders = "*")
public class RegistrationController {
    private RegistrationImpl registration;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserCreationDto userCreationDTO) {
            UserDto savedUser = registration.createUser(userCreationDTO);
            return ResponseEntity.ok(new JsonHelper("success:", "User registered successfully"));
    }
}
