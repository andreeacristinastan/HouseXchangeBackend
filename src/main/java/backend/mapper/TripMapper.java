package backend.mapper;

import backend.dto.trip.*;
import backend.entity.Property;
import backend.entity.Trip;
import backend.entity.User;

public class TripMapper {
    public static TripInfoPropertyDto mapToTripInfoPropertyDto(Trip trip) {
        return new TripInfoPropertyDto(
            trip.getId(),
                trip.getNumberOfPersons(),
                trip.getDestination(),
                trip.getCheckInDate(),
                trip.getCheckOutDate(),
                trip.getUser().getId()
        );
    }

    public static TripInfoUserDto mapToTripInfoUserDto(Trip trip) {
        return new TripInfoUserDto(
            trip.getId(),
                trip.getNumberOfPersons(),
                trip.getDestination(),
                trip.getMinRange(),
                trip.getMaxRange(),
                trip.getCheckInDate(),
                trip.getCheckOutDate(),
                trip.getProperty().getId()
        );
    }

    public static TripInfoDto mapToTripInfoDto(Trip trip) {
        return new TripInfoDto(
                trip.getId(),
                trip.getNumberOfPersons(),
                trip.getDestination(),
                trip.getMinRange(),
                trip.getMaxRange(),
                trip.getCheckInDate(),
                trip.getCheckOutDate(),
                trip.getProperty().getId(),
                trip.getUser().getId()
        );
    }

    public static Trip mapToTrip(TripCreationDto tripCreationDto, User user, Property property) {
        return new Trip(
                tripCreationDto.getNumberOfPersons(),
                tripCreationDto.getDestination(),
                tripCreationDto.getMinRange(),
                tripCreationDto.getMaxRange(),
                tripCreationDto.getCheckInDate(),
                tripCreationDto.getCheckOutDate(),
                user,
                property
        );
    }

    public static TripDto mapToTripDto(Trip trip) {

        return new TripDto(
                trip.getId(),
                trip.getNumberOfPersons(),
                trip.getDestination(),
                trip.getMinRange(),
                trip.getMaxRange(),
                trip.getCheckInDate(),
                trip.getCheckOutDate(),
                trip.getUser().getId(),
                trip.getProperty().getId()
        );
    }
}
