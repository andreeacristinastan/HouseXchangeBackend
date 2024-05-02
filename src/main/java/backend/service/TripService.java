package backend.service;

import backend.dto.trip.TripCreationDto;
import backend.dto.trip.TripDto;
import backend.dto.trip.TripUpdateDto;

import java.util.List;

public interface TripService {
    TripDto createTrip(TripCreationDto tripCreationDto);

    TripDto getTripById(Long tripId);

    List<TripDto> getAllTripsByUser(Long userId, Integer page, Integer size);

    TripDto getTripByUser(Long userId, Long tripId);

    TripDto updateTrip(Long userId, Long tripId, TripUpdateDto tripUpdateDto);

    void deleteTrip(Long userId, Long tripId);
}
