package backend.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import backend.dto.facility.FacilityCreationDto;
import backend.dto.facility.FacilityDto;
import backend.dto.room.RoomDto;
import backend.entity.Facility;
import backend.entity.Property;
import backend.entity.Room;
import backend.entity.User;
import backend.exceptions.DatabaseException;
import backend.exceptions.ResourceNotFoundException;
import backend.mapper.FacilityMapper;
import backend.mapper.RoomMapper;
import backend.repository.FacilityRepository;
import backend.repository.PropertyRepository;
import backend.repository.RoomRepository;
import backend.repository.UserRepository;
import backend.security.AuthUtil;
import backend.service.FacilityService;
import backend.utils.CheckPermissionsHelper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FacilityServiceImpl implements FacilityService {
    private PropertyRepository propertyRepository;
    private RoomRepository roomRepository;
    private UserRepository userRepository;
    private FacilityRepository facilityRepository;
    private AuthUtil authUtil;
    private CheckPermissionsHelper checkPermissionsHelper;

    @Override
    public RoomDto createFacility(Long propertyId, Long roomId, FacilityCreationDto facilityCreationDto) {
        Property property;
        try {
            property = propertyRepository.findById(propertyId)
                    .orElseThrow(() -> new ResourceNotFoundException("Property not found"));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

        Room room;
        try {
            room = roomRepository.findById(roomId)
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

        Facility facility = FacilityMapper.mapToFacility(facilityCreationDto, room);

        try {
            facility = facilityRepository.save(facility);
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

        room.getFacilities().add(facility);

        Room savedRoom;
        try {
            savedRoom = roomRepository.save(room);
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

        return  RoomMapper.mapToRoomDto(savedRoom);
    }

    @Override
    public List<FacilityDto> getAllFacilities(Integer page, Integer size, Long propertyId, Long roomId) {
        try {
            propertyRepository.findById(propertyId)
                    .orElseThrow(() -> new ResourceNotFoundException("Property not found"));

            Room room = roomRepository.findById(roomId)
                    .orElseThrow(() -> new ResourceNotFoundException("Room not found"));

            Pageable pageable = PageRequest.of(page, size);
            Page<Facility> facilityPage = facilityRepository.findByRoom(room, pageable);

            return facilityPage.stream().map(FacilityMapper::mapToFacilityDto)
                    .collect(Collectors.toList());

        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }
    }

    @Override
    public void deleteFacility(Long propertyId, Long roomId, Long facilityId) {
        try {
            Property property = propertyRepository.findById(propertyId)
                    .orElseThrow(() -> new ResourceNotFoundException("Property not found"));

            Room room = roomRepository.findById(roomId)
                    .orElseThrow(() -> new ResourceNotFoundException("Room not found"));

            if(property.getRooms().get(roomId.intValue()) == null) {
                throw new ResourceNotFoundException("Room does not belong to given property");
            }

            User user;
            try {
                user = userRepository.findById(property.getUser().getId())
                        .orElseThrow(() -> new ResourceNotFoundException("User not found"));
            } catch (DataAccessException exception) {
                throw new DatabaseException("Exception occurred while accessing the database", exception);
            }

            checkPermissionsHelper.checkAuth(user.getEmail(), authUtil);

            Facility facility = facilityRepository.findById(facilityId)
                    .orElseThrow(() -> new ResourceNotFoundException("Facility not found"));

            room.getFacilities().remove(facility);

            roomRepository.save(room);
            facilityRepository.delete(facility);
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }
    }

    @Override
    public FacilityDto getFacilityById(Long propertyId, Long roomId, Long facilityId) {
        try {
            propertyRepository.findById(propertyId)
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Property doesn't exists with given id: " + propertyId));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

        try {
            roomRepository.findById(roomId)
                    .orElseThrow(() -> new ResourceNotFoundException("Room doesn't exists with given id: " + roomId));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

        Facility facility;
        try {
           facility = facilityRepository.findById(facilityId)
                    .orElseThrow(() -> new ResourceNotFoundException("Facility doesn't exists with given id: " + facilityId));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

        return FacilityMapper.mapToFacilityDto(facility);

    }
}
