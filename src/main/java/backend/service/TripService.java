package backend.service;

import backend.dto.trip.TripCreationDto;
import backend.dto.trip.TripDto;
import backend.dto.trip.TripUpdateDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TripService {
    TripDto createTrip(TripCreationDto tripCreationDto);

    TripDto getTripById(Long tripId);

    List<TripDto> getAllTripsByUser(Long userId);

    TripDto getTripByUser(Long userId, Long tripId);

    TripDto updateTrip(Long userId, Long tripId, TripUpdateDto tripUpdateDto);

    void deleteTrip(Long userId, Long tripId);

    Page<TripDto> getAllTripsByUserPageable(Long userId, Integer page, Integer size);

    List<TripDto> getAllTrips();

    void deleteAllTripsByProperty(Long propertyId);
}
