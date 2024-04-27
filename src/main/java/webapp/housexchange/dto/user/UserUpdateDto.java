package webapp.housexchange.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import webapp.housexchange.entity.Property;

import java.util.List;
@AllArgsConstructor
@Getter
@Setter
public class UserUpdateDto {
    private String role;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String language;
    private String phoneNumber;

}
