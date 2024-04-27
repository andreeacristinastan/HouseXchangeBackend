package webapp.housexchange.mapper;

import webapp.housexchange.dto.facility.FacilityDto;
import webapp.housexchange.dto.room.RoomCreationDto;
import webapp.housexchange.dto.room.RoomDto;
import webapp.housexchange.entity.Property;
import webapp.housexchange.entity.Room;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class RoomMapper {
    public static Room mapToRoom(RoomCreationDto roomCreationDto, Property property) {
        return new Room(
                roomCreationDto.getAccessibility(),
                property,
                new ArrayList<>()
        );
    }

    public static RoomDto mapToRoomDto(Room room) {
        return new RoomDto(
                room.getId(),
                room.getAccessibility(),
                room.getProperty().getId(),
                room.getFacilities().stream().map(f -> new FacilityDto(
                        f.getId(),
                        f.getNameOfFacility(),
                        f.getRoom().getId()
                )).collect(Collectors.toList())
        );
    }
}
