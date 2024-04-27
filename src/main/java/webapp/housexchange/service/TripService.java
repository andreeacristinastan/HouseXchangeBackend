package webapp.housexchange.service;

import webapp.housexchange.dto.trip.TripCreationDto;
import webapp.housexchange.dto.trip.TripDto;
import webapp.housexchange.dto.trip.TripUpdateDto;

import java.util.List;

public interface TripService {
    TripDto createTrip(TripCreationDto tripCreationDto);

    TripDto getTripById(Long tripId);

    List<TripDto> getAllTripsByUser(Long userId, Integer page, Integer size);

    TripDto getTripByUser(Long userId, Long tripId);

    TripDto updateTrip(Long userId, Long tripId, TripUpdateDto tripUpdateDto);

    void deleteTrip(Long userId, Long tripId);
}
