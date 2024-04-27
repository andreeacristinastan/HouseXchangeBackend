package webapp.housexchange.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import webapp.housexchange.dto.property.PropertyDto;
import webapp.housexchange.dto.room.RoomCreationDto;
import webapp.housexchange.dto.room.RoomDto;
import webapp.housexchange.entity.Property;
import webapp.housexchange.entity.Room;
import webapp.housexchange.entity.User;
import webapp.housexchange.exceptions.DatabaseException;
import webapp.housexchange.exceptions.ResourceNotFoundException;
import webapp.housexchange.mapper.PropertyMapper;
import webapp.housexchange.mapper.RoomMapper;
import webapp.housexchange.repository.PropertyRepository;
import webapp.housexchange.repository.RoomRepository;
import webapp.housexchange.repository.UserRepository;
import webapp.housexchange.security.AuthUtil;
import webapp.housexchange.service.RoomService;
import webapp.housexchange.utils.CheckPermissionsHelper;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class RoomServiceImpl implements RoomService {
    private PropertyRepository propertyRepository;
    private UserRepository userRepository;
    private RoomRepository roomRepository;
    private AuthUtil authUtil;
    private CheckPermissionsHelper checkPermissionsHelper;

    @Override
    public PropertyDto createRoom(Long propertyId, RoomCreationDto roomCreationDto) {
        Property property;
        try {
            property = propertyRepository.findById(propertyId)
                    .orElseThrow(() -> new ResourceNotFoundException("Property not found"));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

        User user;
        try {
            user = userRepository.findById(property.getUser().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

        checkPermissionsHelper.checkAuth(user.getEmail(), authUtil);
        checkPermissionsHelper.checkUser(user.getRole(), authUtil);

        Room room = RoomMapper.mapToRoom(roomCreationDto, property);

        try {
            room = roomRepository.save(room);
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

        property.getRooms().add(room);
        property.setNumberOfRooms(property.getRooms().size());

        Property savedProperty;
        try {
            savedProperty = propertyRepository.save(property);
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

        return PropertyMapper.mapToPropertyDto(savedProperty);
    }

    @Override
    public List<RoomDto> getAllRooms(Long propertyId, Integer page, Integer size) {
        Property property;
        try {
        property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found"));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

        Pageable pageable = PageRequest.of(page, size);
        Page<Room> roomPage = roomRepository.findByProperty(property, pageable);

        return roomPage.stream().map(RoomMapper::mapToRoomDto)
                .collect(Collectors.toList());
    }

    @Override
    public RoomDto getRoomById(Long propertyId, Long roomId) {
        try {
            propertyRepository.findById(propertyId)
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Property doesn't exists with given id: " + propertyId));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

        Room room;
        try {
            room = roomRepository.findById(roomId)
                    .orElseThrow(() -> new ResourceNotFoundException("Room doesn't exists with given id: " + roomId));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

        return RoomMapper.mapToRoomDto(room);
    }

    @Override
    public RoomDto updateRoom(Long propertyId, Long roomId, RoomCreationDto updatedRoom) {
        Property property;
        try {
            property = propertyRepository.findById(propertyId).orElseThrow(
                    () -> new ResourceNotFoundException("Property is not exists with given id: " + propertyId)
            );
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

        Room room;
        try {
            room = roomRepository.findById(roomId)
                    .orElseThrow(() -> new ResourceNotFoundException("Room doesn't exists with given id: " + roomId));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

        User user;
        try {
            user = userRepository.findById(property.getUser().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

        checkPermissionsHelper.checkAuth(user.getEmail(), authUtil);
        checkPermissionsHelper.checkUser(user.getRole(), authUtil);

        if(property.getRooms().stream().anyMatch(currRoom -> currRoom.getId() == roomId)) {
            room.setAccessibility(updatedRoom.getAccessibility());

            Room updatedRoomObj;
            try {
                updatedRoomObj = roomRepository.save(room);
            } catch (DataAccessException exception) {
                throw new DatabaseException("Exception occurred while accessing the database", exception);
            }
            return RoomMapper.mapToRoomDto(updatedRoomObj);
        } else {
            throw new ResourceNotFoundException("Room not found for the given id");
        }


    }

    @Override
    public void deleteRoom(Long propertyId, Long roomId) {
        Property property;
        try {
            property = propertyRepository.findById(propertyId)
                    .orElseThrow(() -> new ResourceNotFoundException("Property not found"));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }
        Room room;
        try {
            room = property.getRooms().stream()
                    .filter(r -> r.getId() == roomId)
                    .findFirst()
                    .orElseThrow(() -> new ResourceNotFoundException("Room not found"));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

        User user;
        try {
            user = userRepository.findById(property.getUser().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

        checkPermissionsHelper.checkAuth(user.getEmail(), authUtil);
        checkPermissionsHelper.checkUser(user.getRole(), authUtil);

        property.getRooms().remove(room);
        property.setNumberOfRooms(property.getRooms().size());

        try {
            propertyRepository.save(property);
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }
        roomRepository.delete(room);
    }
}
