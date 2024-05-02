package backend.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import backend.dto.trip.TripInfoUserDto;
import backend.entity.Property;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String role;
    private String email;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String language;
    private String phoneNumber;
    private List<Property> properties;
    private List <TripInfoUserDto> tripInfoDto;

}
