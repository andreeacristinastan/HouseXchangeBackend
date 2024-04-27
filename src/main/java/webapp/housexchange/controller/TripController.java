package webapp.housexchange.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import webapp.housexchange.dto.trip.TripCreationDto;
import webapp.housexchange.dto.trip.TripDto;
import webapp.housexchange.dto.trip.TripUpdateDto;
import webapp.housexchange.dto.user.UserCreationDto;
import webapp.housexchange.dto.user.UserDto;
import webapp.housexchange.dto.user.UserOutputDto;
import webapp.housexchange.dto.user.UserUpdateDto;
import webapp.housexchange.service.TripService;
import webapp.housexchange.service.UserService;

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
    @GetMapping("/api/users/{user_id}/trips")
    public ResponseEntity<List<TripDto>> getAllTripsByUser(@RequestParam(defaultValue = "0") Integer page,
                                                           @RequestParam(defaultValue = "10") Integer size,
                                                           @PathVariable("user_id") Long userId) {
        List<TripDto> trips = tripService.getAllTripsByUser(userId, page, size);

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
    @PutMapping("/api/users/{user_id}/trips/{trip_id}")
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
