package backend.service.impl;

import backend.dto.property.PropertyDto;
import backend.mapper.PropertyMapper;
import backend.utils.LoggerHelper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
import java.time.ZoneId;
import java.util.Date;
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

//        checkPermissionsHelper.checkAuth(user.getUsername(), authUtil);

        if(tripCreationDto.getCheckInDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isAfter(tripCreationDto.getCheckOutDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())) {
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

//        checkPermissionsHelper.checkAuth(trip.getUser().getEmail(), authUtil);
        return TripMapper.mapToTripDto(trip);
    }

    @Override
    public List<TripDto> getAllTripsByUser(Long userId) {
        User user;
        try {
            user = userRepository.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

//        checkPermissionsHelper.checkAuth(user.getUsername(), authUtil);

        List<Trip> trips = tripRepository.findByUser(user);

        return trips.stream().map(TripMapper::mapToTripDto)
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
//        checkPermissionsHelper.checkAuth(user.getUsername(), authUtil);

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

//        checkPermissionsHelper.checkAuth(user.getUsername(), authUtil);

        Trip trip;
        try {
            trip = tripRepository.findById(tripId)
                    .orElseThrow(() -> new ResourceNotFoundException("Trip not found"));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

        if (LocalDate.now().isAfter(trip.getCheckInDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())) {
            throw new BadRequestException("Cannot update a trip that has already started");
        }

        if(tripUpdateDto.getCheckInDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isAfter(tripUpdateDto.getCheckOutDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())) {
            throw new IllegalArgumentException("Check-in date should be before check-out date");
        }

        if(user.getTrips().stream().anyMatch(currTrip -> currTrip.getId() == tripId)) {


            if(tripUpdateDto.getNumberOfPersons() != 0) {
                trip.setNumberOfPersons(tripUpdateDto.getNumberOfPersons());

            }

            if(tripUpdateDto.getMinRange() != 0) {
                trip.setMinRange(tripUpdateDto.getMinRange());

            }

            if(tripUpdateDto.getMaxRange() != 0 ) {
                trip.setMaxRange(tripUpdateDto.getMaxRange());

            }

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

//        checkPermissionsHelper.checkAuth(user.getUsername(), authUtil);

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

        if (LocalDate.now().isBefore(trip.getCheckInDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())) {
//                && user.getTrips().stream().anyMatch(currTrip -> currTrip.getId() == tripId)) {

            property.getTrips().stream()
                    .filter(t -> t.getId() == tripId)
                    .findFirst()
                    .orElseThrow(() -> new ResourceNotFoundException("Trip not found"));

                user.getTrips().remove(trip);
                try {
                    userRepository.save(user);
                } catch (DataAccessException exception) {
                    throw new DatabaseException("Exception occurred while accessing the database", exception);
                }

                property.getTrips().remove(trip);

                try {
                    propertyRepository.save(property);
                } catch (DataAccessException exception) {
                    throw new DatabaseException("Exception occurred while accessing the database", exception);
                }

        } else {
            throw new BadRequestException("Cannot delete a trip that has already started");
        }

    }

    @Override
    public Page<TripDto> getAllTripsByUserPageable(Long userId, Integer page, Integer size) {

        User user;
        try {
            user = userRepository.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

//        checkPermissionsHelper.checkAuth(user.getUsername(), authUtil);
        Pageable pageable = PageRequest.of(page, size);
        Page<Trip> tripPage = tripRepository.findByUser(user, pageable);
        List<TripDto> trips = tripPage.stream()
                .map(TripMapper::mapToTripDto)
                .collect(Collectors.toList());

        return new PageImpl<>(trips, pageable, tripPage.getTotalElements());

    }

    @Override
    public List<TripDto> getAllTrips() {
        List<Trip> trips = tripRepository.findAll();
//        System.out.println("get all");
        return trips.stream().map(TripMapper::mapToTripDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAllTripsByProperty(Long propertyId) {
        Property property;
        try {
            property = propertyRepository.findById(propertyId)
                    .orElseThrow(() -> new ResourceNotFoundException("Property not found"));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

        // Fetch all trips associated with this property
        List<Trip> trips = tripRepository.findByPropertyId(propertyId);

        for (Trip trip : trips) {
            User user = trip.getUser();

            // Remove trip from user's list of trips
            user.getTrips().remove(trip);
            try {
                userRepository.save(user);
            } catch (DataAccessException exception) {
                throw new DatabaseException("Exception occurred while saving the user", exception);
            }

            // Remove trip from property's list of trips
            property.getTrips().remove(trip);
        }

        try {
            propertyRepository.save(property);
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while saving the property", exception);
        }

        // Finally, delete all the trips
        try {
            tripRepository.deleteAll(trips);
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while deleting the trips", exception);
        }
    }

    @Override
    public void deleteTripSingle(Long tripId) {
        Trip trip;
        try {
            trip = tripRepository.findById(tripId)
                    .orElseThrow(() -> new ResourceNotFoundException("Trip not found"));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }


        Property property = trip.getProperty();

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
    }
}
