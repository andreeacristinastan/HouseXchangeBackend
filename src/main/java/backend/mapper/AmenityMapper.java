package backend.mapper;

import backend.dto.amenity.AmenityCreationDto;
import backend.dto.amenity.AmenityDto;
import backend.dto.amenity.AmenityInfoDto;
import backend.entity.Amenity;
import backend.entity.Property;

public class AmenityMapper {
    public static AmenityInfoDto mapToAmenityInfoDto(Amenity amenity) {
        return new AmenityInfoDto(
                amenity.getId(),
                amenity.getGym(),
                amenity.getSwimmingPool(),
                amenity.getGarden(),
                amenity.getParking(),
                amenity.getWifi(),
                amenity.getBikes(),
                amenity.getKidsZone(),
                amenity.getPetsFriendly(),
                amenity.getDisabilitiesFriendly()
        );
    }

    public static Amenity mapToAmenity(AmenityCreationDto amenityCreationDto, Property property) {
        return new Amenity(
                amenityCreationDto.getGym(),
                amenityCreationDto.getSwimmingPool(),
                amenityCreationDto.getGarden(),
                amenityCreationDto.getParking(),
                amenityCreationDto.getWifi(),
                amenityCreationDto.getBikes(),
                amenityCreationDto.getKidsZone(),
                amenityCreationDto.getPetsFriendly(),
                amenityCreationDto.getDisabilitiesFriendly(),
                property
        );
    }

    public static AmenityDto mapToAmenityDto(Amenity amenity) {
        return new AmenityDto(
                amenity.getId(),
                amenity.getGym(),
                amenity.getSwimmingPool(),
                amenity.getGarden(),
                amenity.getParking(),
                amenity.getWifi(),
                amenity.getBikes(),
                amenity.getKidsZone(),
                amenity.getPetsFriendly(),
                amenity.getDisabilitiesFriendly(),
                PropertyMapper.mapToPropertyInfoDto(amenity.getProperty())
        );
    }
}
