package webapp.housexchange.mapper;

import webapp.housexchange.dto.property.PropertyInfoDto;
import webapp.housexchange.dto.trip.TripInfoUserDto;
import webapp.housexchange.dto.user.UserCreationDto;
import webapp.housexchange.dto.user.UserDto;
import webapp.housexchange.dto.user.UserOutputDto;
import webapp.housexchange.entity.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserMapper {
    public static UserDto mapToUserDto(User user) {

        return new UserDto(
                user.getId(),
                user.getRole(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getLanguage(),
                user.getPhoneNumber(),
                user.getProperties(),
                Optional.ofNullable(user.getTrips()).orElse(Collections.emptyList()).stream().map(t -> new TripInfoUserDto(
                        t.getId(),
                        t.getNumberOfPersons(),
                        t.getDestination(),
                        t.getMinRange(),
                        t.getMaxRange(),
                        t.getCheckInDate(),
                        t.getCheckOutDate(),
                        t.getProperty().getId()
                )).collect(Collectors.toList())


        );
    }

    public static UserOutputDto mapToUserOutputDto(User user) {
        return new UserOutputDto(
                user.getId(),
                user.getRole(),
                user.getEmail(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getLanguage(),
                user.getPhoneNumber(),
                user.getProperties().stream().map(p -> new PropertyInfoDto(
                        p.getId(),
                        p.getName()
                )).collect(Collectors.toList()),
                user.getTrips().stream().map(t -> new TripInfoUserDto(
                        t.getId(),
                        t.getNumberOfPersons(),
                        t.getDestination(),
                        t.getMinRange(),
                        t.getMaxRange(),
                        t.getCheckInDate(),
                        t.getCheckOutDate(),
                        t.getProperty().getId()
                )).collect(Collectors.toList())
        );
    }

    public static User mapToUser(UserCreationDto userDto) {
        return new User(
                userDto.getRole(),
                userDto.getEmail(),
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getLanguage(),
                userDto.getPhoneNumber(),
                new ArrayList<>()
        );
    }
}
