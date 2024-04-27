package webapp.housexchange.service;

import webapp.housexchange.dto.property.PropertyDto;
import webapp.housexchange.dto.room.RoomCreationDto;
import webapp.housexchange.dto.room.RoomDto;

import java.util.List;

public interface RoomService {
    PropertyDto createRoom(Long propertyId, RoomCreationDto roomCreationDto);

    List<RoomDto> getAllRooms(Long propertyId, Integer page, Integer size);

    RoomDto getRoomById(Long propertyId, Long roomId);

    RoomDto updateRoom(Long propertyId, Long roomId, RoomCreationDto updatedRoom);

    void deleteRoom(Long propertyId, Long roomId);
}
