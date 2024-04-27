package webapp.housexchange.mapper;

import webapp.housexchange.dto.property.PropertyCreationDto;
import webapp.housexchange.dto.property.PropertyDto;
import webapp.housexchange.dto.property.PropertyInfoDto;
import webapp.housexchange.dto.room.RoomInfoDto;
import webapp.housexchange.dto.trip.TripInfoPropertyDto;
import webapp.housexchange.entity.Property;
import webapp.housexchange.entity.User;
import webapp.housexchange.repository.TripRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PropertyMapper {

    public static PropertyDto mapToPropertyDto(Property property) {
        List<TripInfoPropertyDto> tripInfoPropertyDtos = null;
        if(property.getTrips() != null) {
            tripInfoPropertyDtos = property.getTrips().stream().map(t -> new TripInfoPropertyDto(
                    t.getId(),
                    t.getNumberOfPersons(),
                    t.getDestination(),
                    t.getCheckInDate(),
                    t.getCheckOutDate(),
                    t.getUser().getId()
            )).collect(Collectors.toList());
        }

        return new PropertyDto(
                property.getId(),
                property.getName(),
                property.getCountry(),
                property.getCity(),
                property.getAddress(),
                property.getDistance(),
                property.getAllowedPet(),
                property.getAccessibility(),
                property.getNumberOfRooms(),
                property.getUser().getId(),
                property.getRooms().stream().map(r -> new RoomInfoDto(
                        r.getId()
                )).collect(Collectors.toList()),
                tripInfoPropertyDtos,
                Optional.ofNullable(property.getMeal()).map(MealMapper::mapToMealInfoDto).orElse(null),
                Optional.ofNullable(property.getAmenity()).map(AmenityMapper::mapToAmenityInfoDto).orElse(null)
        );
    }

    public static Property mapToProperty(PropertyCreationDto propertyCreationDto, User user) {
        return new Property(
                propertyCreationDto.getName(),
                propertyCreationDto.getCountry(),
                propertyCreationDto.getCity(),
                propertyCreationDto.getAddress(),
                propertyCreationDto.getDistance(),
                propertyCreationDto.getAllowedPet(),
                propertyCreationDto.getAccessibility(),
                user,
                new ArrayList<>()
                );
    }

    public static PropertyInfoDto mapToPropertyInfoDto(Property property) {
        return new PropertyInfoDto(
                property.getId(),
                property.getName()
                );
    }
}
