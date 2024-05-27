package backend.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import backend.dto.property.PropertyInfoDto;
import backend.dto.trip.TripInfoUserDto;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class UserOutputDto {
    private Long id;
    private String role;
    private String email;
    private String username;
    private String firstName;
    private String lastName;
    private String language;
    private Integer phoneNumber;
    private String prefixNumber;
    private List<PropertyInfoDto> properties;
    private List <TripInfoUserDto> tripInfoDto;

}
