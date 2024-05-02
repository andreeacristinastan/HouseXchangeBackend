package backend.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import backend.dto.property.PropertyDto;
import backend.dto.room.RoomCreationDto;
import backend.dto.room.RoomDto;
import backend.service.RoomService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/properties")
public class RoomController {
    private RoomService roomService;

    //Add a room to a specific property
    @PostMapping("{property_id}/rooms")
    public ResponseEntity<PropertyDto> createRoom(@PathVariable("property_id") Long propertyId,
                                                  @RequestBody RoomCreationDto roomCreationDto) {
        PropertyDto savedRoom = roomService.createRoom(propertyId, roomCreationDto);
        return new ResponseEntity<>(savedRoom, HttpStatus.CREATED);
    }

    //Get all rooms from specific property REST API
    @GetMapping("{property_id}/rooms")
    public ResponseEntity<List<RoomDto>> getAllRooms(@RequestParam(defaultValue = "0") Integer page,
                                                     @RequestParam(defaultValue = "10") Integer size,
                                                     @PathVariable("property_id") Long propertyId) {
        List<RoomDto> rooms = roomService.getAllRooms(propertyId, page, size);

        return ResponseEntity.ok(rooms);
    }

    //Get a room by id REST API
    @GetMapping("{property_id}/rooms/{room_id}")
    public ResponseEntity<RoomDto> getRoomById(@PathVariable("property_id") Long propertyId,
                                               @PathVariable("room_id") Long roomId) {
        RoomDto roomDto = roomService.getRoomById(propertyId, roomId);

        return ResponseEntity.ok(roomDto);
    }

    // Update room REST API
    @PutMapping("{property_id}/rooms/{room_id}")
    public ResponseEntity<RoomDto> updateRoom(@PathVariable("property_id") Long propertyId,
                                              @PathVariable("room_id") Long roomId,
                                              @RequestBody RoomCreationDto updatedRoom) {
        RoomDto roomDto = roomService.updateRoom(propertyId, roomId, updatedRoom);

        return ResponseEntity.ok(roomDto);
    }

    //Delete a room from a specific property REST API
    @DeleteMapping("{property_id}/rooms/{room_id}")
    public ResponseEntity<String> deleteRoom(@PathVariable("property_id") Long propertyId,
                                             @PathVariable("room_id") Long roomId) {
        roomService.deleteRoom(propertyId, roomId);
        return ResponseEntity.ok("Room deleted successfully");
    }
}
