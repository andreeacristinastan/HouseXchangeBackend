package webapp.housexchange.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import webapp.housexchange.dto.property.PropertyInfoDto;
import webapp.housexchange.dto.trip.TripInfoPropertyDto;
import webapp.housexchange.dto.trip.TripInfoUserDto;

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
    private String phoneNumber;
    private List<PropertyInfoDto> properties;
    private List <TripInfoUserDto> tripInfoDto;

}
