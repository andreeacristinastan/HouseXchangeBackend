package backend.controller;

import backend.dto.property.PropertyDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import backend.dto.trip.TripCreationDto;
import backend.dto.trip.TripDto;
import backend.dto.trip.TripUpdateDto;
import backend.service.TripService;

import java.util.List;

@RestController
//@RequestMapping("/api/trip")
@AllArgsConstructor
public class TripController {
    private TripService tripService;

    //Add trip REST API
    @PreAuthorize("hasRole('GUEST') or hasRole('HOST')")
    @PostMapping("/api/trip")
    public ResponseEntity<TripDto> createTrip(@RequestBody TripCreationDto tripCreationDto) {
        TripDto savedTrip = tripService.createTrip(tripCreationDto);
        return new ResponseEntity<>(savedTrip, HttpStatus.CREATED);
    }

    //Get trip REST API
    @GetMapping("/api/trip/{trip_id}")
    public ResponseEntity<TripDto> getTripById(@PathVariable("trip_id") Long tripId) {
        TripDto tripDto = tripService.getTripById(tripId);

        return ResponseEntity.ok(tripDto);
    }

    //Get All trips for a user REST API
    @GetMapping("/api/users/{user_id}/trips-all")
    public ResponseEntity<List<TripDto>> getAllTripsByUser(
                                                           @PathVariable("user_id") Long userId) {
        List<TripDto> trips = tripService.getAllTripsByUser(userId);

        return ResponseEntity.ok(trips);
    }

    //Get all properties from database REST API
    @GetMapping("/api/users/{user_id}/trips")
    public ResponseEntity<Page<TripDto>> getAllTripsByUserPageable(@RequestParam(defaultValue = "0") Integer page,
                                                                      @RequestParam(defaultValue = "10") Integer size,
                                                                   @PathVariable("user_id") Long userId) {
        Page<TripDto> trips = tripService.getAllTripsByUserPageable(userId, page, size);

        return ResponseEntity.ok(trips);
    }

@DeleteMapping("/api/properties/{property_id}/trips")
    public ResponseEntity<String> deleteAllTripsByProperty(@PathVariable("property_id") Long propertyId) {
        tripService.deleteAllTripsByProperty(propertyId);

        return ResponseEntity.ok("Trips deleted successfully!");
    }

    @GetMapping("/api/trips")
    public ResponseEntity<List<TripDto>> getAllTrips() {
        List<TripDto> trips = tripService.getAllTrips();

        return ResponseEntity.ok(trips);
    }

    //Get trip for a user REST API
    @GetMapping("/api/users/{user_id}/trips/{trip_id}")
    public ResponseEntity<TripDto> getTripByUser(@PathVariable("user_id") Long userId,
                                                 @PathVariable("trip_id") Long tripId) {
        TripDto tripDto = tripService.getTripByUser(userId, tripId);

        return ResponseEntity.ok(tripDto);
    }

    // Update trip REST API
    @PatchMapping("/api/users/{user_id}/trips/{trip_id}")
    public ResponseEntity<TripDto> updateTrip(@PathVariable("user_id") Long userId,
                                              @PathVariable("trip_id") Long tripId,
                                              @RequestBody TripUpdateDto tripUpdateDto) {
        TripDto tripDto = tripService.updateTrip(userId, tripId, tripUpdateDto);

        return ResponseEntity.ok(tripDto);
    }

    //Delete trip REST API
    @DeleteMapping("/api/users/{user_id}/trips/{trip_id}")
    public ResponseEntity<String> deleteTrip(@PathVariable("user_id") Long userId,
                                             @PathVariable("trip_id") Long tripId) {
        tripService.deleteTrip(userId, tripId);

        return ResponseEntity.ok("Trip deleted successfully");
    }
}
