package webapp.housexchange.mapper;

import webapp.housexchange.dto.amenity.AmenityCreationDto;
import webapp.housexchange.dto.amenity.AmenityDto;
import webapp.housexchange.dto.amenity.AmenityInfoDto;
import webapp.housexchange.entity.Amenity;
import webapp.housexchange.entity.Property;

public class AmenityMapper {
    public static AmenityInfoDto mapToAmenityInfoDto(Amenity amenity) {
        return new AmenityInfoDto(
                amenity.getId(),
                amenity.getGym(),
                amenity.getSwimmingPool(),
                amenity.getGarden(),
                amenity.getParking(),
                amenity.getWireless(),
                amenity.getBikes(),
                amenity.getKidsZone()
        );
    }

    public static Amenity mapToAmenity(AmenityCreationDto amenityCreationDto, Property property) {
        return new Amenity(
                amenityCreationDto.getGym(),
                amenityCreationDto.getSwimmingPool(),
                amenityCreationDto.getGarden(),
                amenityCreationDto.getParking(),
                amenityCreationDto.getWireless(),
                amenityCreationDto.getBikes(),
                amenityCreationDto.getKidsZone(),
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
                amenity.getWireless(),
                amenity.getBikes(),
                amenity.getKidsZone(),
                PropertyMapper.mapToPropertyInfoDto(amenity.getProperty())
        );
    }
}
