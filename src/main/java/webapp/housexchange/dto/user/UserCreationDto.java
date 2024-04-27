package webapp.housexchange.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserCreationDto {
    private String role;
    private String email;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String language;
    private String phoneNumber;
}
