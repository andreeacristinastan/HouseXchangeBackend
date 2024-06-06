package backend.mapper;

import backend.dto.image.ImageInfoDto;
import backend.dto.property.PropertyCreationDto;
import backend.dto.property.PropertyDto;
import backend.dto.property.PropertyInfoDto;
import backend.dto.room.RoomInfoDto;
import backend.dto.trip.TripInfoPropertyDto;
import backend.entity.Amenity;
import backend.entity.Property;
import backend.entity.User;

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

        List<ImageInfoDto> imageInfoDtos = null;
        if(property.getImages() != null) {
            imageInfoDtos = property.getImages().stream().map(i -> new ImageInfoDto(
                    i.getId(),
                    i.getUrl()

            )).collect(Collectors.toList());
        }

        return new PropertyDto(
                property.getId(),
                property.getName(),
                property.getCountry(),
                property.getCity(),
                property.getAddress(),
                property.getZipCode(),
                property.getPropertyDescription(),
                property.getPropertyType(),
                property.getNumberOfBathrooms(),
                property.getNumberOfRooms(),
                property.getPrice(),
                property.getUser().getId(),
                tripInfoPropertyDtos,
                imageInfoDtos,
                Optional.ofNullable(property.getMeal()).map(MealMapper::mapToMealInfoDto).orElse(null),
                Optional.ofNullable(property.getAmenity()).map(AmenityMapper::mapToAmenityInfoDto).orElse(null),
                Optional.ofNullable(property.getFacility()).map(FacilityMapper::mapToFacilityDto).orElse(null)
        );
    }

    public static Property mapToProperty(PropertyCreationDto propertyCreationDto, User user) {
        return new Property(
                propertyCreationDto.getName(),
                propertyCreationDto.getPropertyType(),
                propertyCreationDto.getPropertyDescription(),
                propertyCreationDto.getCountry(),
                propertyCreationDto.getCity(),
                propertyCreationDto.getZipCode(),
                propertyCreationDto.getNumberOfBathrooms(),
                propertyCreationDto.getAddress(),
                propertyCreationDto.getNumberOfRooms(),
                propertyCreationDto.getPrice(),
                user//                new ArrayList<>()
                );
    }

    public static PropertyInfoDto mapToPropertyInfoDto(Property property) {
        return new PropertyInfoDto(
                property.getId(),
                property.getName()
                );
    }
}
