package backend.controller.auth;

import backend.exceptions.BadRequestException;
import backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import backend.dto.auth.AuthResponseDto;
import backend.dto.auth.AuthenticationRequestDto;
import backend.security.JwtUtil;
import backend.service.impl.UserDetailsServiceImpl;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", allowPrivateNetwork = "true", allowedHeaders = "*")
public class LoginController {
    private AuthenticationManager authenticationManager;

    private JwtUtil jwtUtil;

    private UserDetailsServiceImpl userDetailsService;

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> loginUser(@RequestBody AuthenticationRequestDto authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new BadRequestException("Incorrect username or password");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponseDto(jwt));
    }
}
