package backend.service;

import backend.dto.property.PropertyDto;
import backend.dto.room.RoomCreationDto;
import backend.dto.room.RoomDto;

import java.util.List;

public interface RoomService {
    PropertyDto createRoom(Long propertyId, RoomCreationDto roomCreationDto);

    List<RoomDto> getAllRooms(Long propertyId, Integer page, Integer size);

    RoomDto getRoomById(Long propertyId, Long roomId);

    RoomDto updateRoom(Long propertyId, Long roomId, RoomCreationDto updatedRoom);

    void deleteRoom(Long propertyId, Long roomId);
}
