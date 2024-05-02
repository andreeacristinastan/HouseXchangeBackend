package backend.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import backend.dto.trip.TripCreationDto;
import backend.dto.trip.TripDto;
import backend.dto.trip.TripUpdateDto;
import backend.entity.Property;
import backend.entity.Trip;
import backend.entity.User;
import backend.exceptions.BadRequestException;
import backend.exceptions.DatabaseException;
import backend.exceptions.ResourceNotFoundException;
import backend.mapper.TripMapper;
import backend.repository.PropertyRepository;
import backend.repository.TripRepository;
import backend.repository.UserRepository;
import backend.security.AuthUtil;
import backend.service.TripService;
import backend.utils.CheckPermissionsHelper;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TripServiceImpl implements TripService {
    private UserRepository userRepository;
    private PropertyRepository propertyRepository;
    private TripRepository tripRepository;
    private AuthUtil authUtil;
    private CheckPermissionsHelper checkPermissionsHelper;

    @Override
    public TripDto createTrip(TripCreationDto tripCreationDto) {
        User user;
        try {
            user = userRepository.findById(tripCreationDto.getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

        checkPermissionsHelper.checkAuth(user.getEmail(), authUtil);

        if(tripCreationDto.getCheckInDate().isAfter(tripCreationDto.getCheckOutDate())) {
            throw new IllegalArgumentException("Check-in date should be before check-out date");
        }

        Property property;
        try {
            property = propertyRepository.findById(tripCreationDto.getPropertyId()).orElseThrow(
                () -> new ResourceNotFoundException("Property not found"));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

        Trip trip = TripMapper.mapToTrip(tripCreationDto, user, property);
        trip.setUser(user);
        trip.setProperty(property);

        Trip savedTrip;
        try {
            savedTrip = tripRepository.save(trip);
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }
        return TripMapper.mapToTripDto(savedTrip);

    }

    @Override
    public TripDto getTripById(Long tripId) {
        Trip trip;
        try {
            trip = tripRepository.findById(tripId)
                    .orElseThrow(() -> new ResourceNotFoundException("Trip not found"));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

        checkPermissionsHelper.checkAuth(trip.getUser().getEmail(), authUtil);
        return TripMapper.mapToTripDto(trip);
    }

    @Override
    public List<TripDto> getAllTripsByUser(Long userId, Integer page, Integer size) {
        User user;
        try {
            user = userRepository.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

        checkPermissionsHelper.checkAuth(user.getEmail(), authUtil);
        Pageable pageable = PageRequest.of(page, size);
        Page<Trip> tripPage = tripRepository.findByUser(user, pageable);

        return tripPage.stream().map(TripMapper::mapToTripDto)
                        .collect(Collectors.toList());
    }

    @Override
    public TripDto getTripByUser(Long userId, Long tripId) {
        User user;

        try {
            user = userRepository.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }
        checkPermissionsHelper.checkAuth(user.getEmail(), authUtil);

        Trip trip;
        try {
            trip = tripRepository.findById(tripId)
                    .orElseThrow(() -> new ResourceNotFoundException("Trip not found"));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

        if(user.getTrips().stream().anyMatch(currTrip -> currTrip.getId() == tripId)) {
            return TripMapper.mapToTripDto(trip);
        } else {
            throw new ResourceNotFoundException("Trip not found for the given user");
        }

    }

    @Override
    public TripDto updateTrip(Long userId, Long tripId, TripUpdateDto tripUpdateDto) {
        User user;
        try {
            user = userRepository.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

        checkPermissionsHelper.checkAuth(user.getEmail(), authUtil);

        Trip trip;
        try {
            trip = tripRepository.findById(tripId)
                    .orElseThrow(() -> new ResourceNotFoundException("Trip not found"));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

        if (LocalDate.now().isAfter(trip.getCheckInDate())) {
            throw new BadRequestException("Cannot delete a trip that has already started");
        }

        if(tripUpdateDto.getCheckInDate().isAfter(tripUpdateDto.getCheckOutDate())) {
            throw new IllegalArgumentException("Check-in date should be before check-out date");
        }

        if(user.getTrips().stream().anyMatch(currTrip -> currTrip.getId() == tripId)) {
            trip.setCheckInDate(tripUpdateDto.getCheckInDate());
            trip.setCheckOutDate(tripUpdateDto.getCheckOutDate());
            trip.setNumberOfPersons(tripUpdateDto.getNumberOfPersons());
            trip.setMinRange(tripUpdateDto.getMinRange());
            trip.setMaxRange(tripUpdateDto.getMaxRange());

            Trip updatedObj;
            try {
                updatedObj = tripRepository.save(trip);
            } catch (DataAccessException exception) {
                throw new DatabaseException("Exception occurred while accessing the database", exception);
            }
            return TripMapper.mapToTripDto(updatedObj);
        } else {
            throw new ResourceNotFoundException("Trip does not belong to given user");
        }

    }

    @Override
    public void deleteTrip(Long userId, Long tripId) {
        User user;
        try {
            user = userRepository.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

        checkPermissionsHelper.checkAuth(user.getEmail(), authUtil);

        Trip trip;
        try {
            trip = tripRepository.findById(tripId)
                    .orElseThrow(() -> new ResourceNotFoundException("Trip not found"));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

        user.getTrips().stream()
                .filter(t -> t.getId() == tripId)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Trip does not belong to given user"));

        Property property = trip.getProperty();

        if (LocalDate.now().isBefore(trip.getCheckInDate()) && user.getTrips().stream().anyMatch(currTrip -> currTrip.getId() == tripId)) {
            user.getTrips().remove(trip);

            try {
                userRepository.save(user);
            } catch (DataAccessException exception) {
                throw new DatabaseException("Exception occurred while accessing the database", exception);
            }

            property.getTrips().stream()
                    .filter(t -> t.getId() == tripId)
                    .findFirst()
                    .orElseThrow(() -> new ResourceNotFoundException("Trip not found"));

            property.getTrips().remove(trip);

            try {
                propertyRepository.save(property);
            } catch (DataAccessException exception) {
                throw new DatabaseException("Exception occurred while accessing the database", exception);
            }

            try {
                tripRepository.delete(trip);
            } catch (DataAccessException exception) {
                throw new DatabaseException("Exception occurred while accessing the database", exception);
            }
        } else {
            throw new BadRequestException("Cannot delete a trip that has already started");
        }

    }
}
