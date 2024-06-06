package backend.mapper;

import backend.dto.image.ImageInfoDto;
import backend.dto.property.PropertyInfoDto;
import backend.dto.trip.TripInfoUserDto;
import backend.dto.user.UserCreationDto;
import backend.dto.user.UserDto;
import backend.dto.user.UserOutputDto;
import backend.entity.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
                user.getPrefixNumber(),
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
                )).collect(Collectors.toList()),
                ProfileImageMapper.mapToImageDto(user.getProfileImage())


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
                user.getPrefixNumber(),
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
                )).collect(Collectors.toList()),
                ProfileImageMapper.mapToImageDto(user.getProfileImage())
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
                userDto.getPrefixNumber(),
                new ArrayList<>()
        );
    }
}
