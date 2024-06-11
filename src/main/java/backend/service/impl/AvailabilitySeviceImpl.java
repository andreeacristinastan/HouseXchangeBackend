package backend.service.impl;

import backend.dto.availability.AvailabilityCreationDto;
import backend.dto.availability.AvailabilityDto;
import backend.entity.*;
import backend.exceptions.BadRequestException;
import backend.exceptions.DatabaseException;
import backend.exceptions.ResourceNotFoundException;
import backend.mapper.AvailabilityMapper;
import backend.mapper.FeedbackMapper;
import backend.mapper.TripMapper;
import backend.mapper.UserMapper;
import backend.repository.AvailabilityRepository;
import backend.repository.FeedbackRepository;
import backend.repository.UserRepository;
import backend.security.AuthUtil;
import backend.service.AvailabilityService;
import backend.utils.CheckPermissionsHelper;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AvailabilitySeviceImpl implements AvailabilityService {
    AvailabilityRepository availabilityRepository;
    UserRepository userRepository;
    private AuthUtil authUtil;
    private CheckPermissionsHelper checkPermissionsHelper;

    @Override
    public AvailabilityDto createAvailability(AvailabilityCreationDto availabilityCreationDto) {
        User user;
        try {
            user = userRepository.findById(availabilityCreationDto.getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

//        checkPermissionsHelper.checkAuth(user.getUsername(), authUtil);

        Availability availability = AvailabilityMapper.mapToAvailability(availabilityCreationDto);

        Availability savedAvailability;
        try {
            savedAvailability = availabilityRepository.save(availability);
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }
        return AvailabilityMapper.mapToAvailabilityDto(savedAvailability);
    }

    @Override
    public List<AvailabilityDto> getAllAvailabilities() {
        List<Availability> availabilities = availabilityRepository.findAll();
        return availabilities.stream().map(AvailabilityMapper::mapToAvailabilityDto)
                .collect(Collectors.toList());
    }

    @Override
    public AvailabilityDto updateAvailability(Long availabilityId, AvailabilityCreationDto availabilityUpdateDto) {

        Availability availability;
        try {
            availability = availabilityRepository.findById(availabilityId)
                    .orElseThrow(() -> new ResourceNotFoundException("Availability not found"));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }


            if(availabilityUpdateDto.getStartDate() != null) {
                availability.setStartDate(availabilityUpdateDto.getStartDate());

            }

            if(availabilityUpdateDto.getEndDate() != null) {
                availability.setEndDate(availabilityUpdateDto.getEndDate());

            }

            Availability updatedObj;
            try {
                updatedObj = availabilityRepository.save(availability);
            } catch (DataAccessException exception) {
                throw new DatabaseException("Exception occurred while accessing the database", exception);
            }
            return AvailabilityMapper.mapToAvailabilityDto(updatedObj);

    }

    @Override
    public void deleteAvailability(Long availabilityId) {

        Availability availability;
        try {
            availability = availabilityRepository.findById(availabilityId)
                    .orElseThrow(() -> new ResourceNotFoundException("Availability not found"));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }
        availabilityRepository.deleteById(availabilityId);
    }
}
