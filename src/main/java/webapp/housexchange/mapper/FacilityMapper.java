package webapp.housexchange.mapper;

import webapp.housexchange.dto.facility.FacilityCreationDto;
import webapp.housexchange.dto.facility.FacilityDto;
import webapp.housexchange.dto.room.RoomCreationDto;
import webapp.housexchange.entity.Facility;
import webapp.housexchange.entity.Property;
import webapp.housexchange.entity.Room;

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
