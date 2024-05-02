package backend.mapper;

import backend.dto.facility.FacilityCreationDto;
import backend.dto.facility.FacilityDto;
import backend.entity.Facility;
import backend.entity.Room;

public class FacilityMapper {

    public static Facility mapToFacility(FacilityCreationDto facilityCreationDto, Room room) {
        return new Facility(
                facilityCreationDto.getNameOfFacility(),
                room
        );
    }

    public static FacilityDto mapToFacilityDto(Facility facility) {
        return new FacilityDto(
                facility.getId(),
                facility.getNameOfFacility(),
                facility.getRoom().getId()
        );
    }
}
