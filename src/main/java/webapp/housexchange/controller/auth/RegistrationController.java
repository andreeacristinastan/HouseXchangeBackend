package webapp.housexchange.controller.auth;

import lombok.AllArgsConstructor;
import netscape.javascript.JSObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import webapp.housexchange.dto.user.UserCreationDto;
import webapp.housexchange.dto.user.UserDto;
import webapp.housexchange.service.impl.RegistrationImpl;
import webapp.housexchange.utils.JsonHelper;

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
