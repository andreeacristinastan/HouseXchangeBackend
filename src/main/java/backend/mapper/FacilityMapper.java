package backend.mapper;

import backend.dto.facility.FacilityCreationDto;
import backend.dto.facility.FacilityDto;
import backend.entity.Facility;
import backend.entity.Property;
//import backend.entity.Room;

public class FacilityMapper {

    public static Facility mapToFacility(FacilityCreationDto facilityCreationDto, Property property) {
        return new Facility(
                facilityCreationDto.getTowel(),
                facilityCreationDto.getBalcony(),
                facilityCreationDto.getAirConditioning(),
                facilityCreationDto.getTv(),
                property
        );
    }

    public static FacilityDto mapToFacilityDto(Facility facility) {
        return new FacilityDto(
                facility.getId(),
                facility.getTowel(),
                facility.getBalcony(),
                facility.getAirConditioning(),
                facility.getTv(),
                PropertyMapper.mapToPropertyInfoDto(facility.getProperty())
        );
    }
}
