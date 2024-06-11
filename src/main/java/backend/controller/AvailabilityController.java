package backend.controller;

import backend.dto.availability.AvailabilityCreationDto;
import backend.dto.availability.AvailabilityDto;
import backend.dto.trip.TripCreationDto;
import backend.dto.trip.TripDto;
import backend.dto.user.UserOutputDto;
import backend.dto.user.UserUpdateDto;
import backend.repository.AvailabilityRepository;
import backend.service.AvailabilityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", allowPrivateNetwork = "true", allowedHeaders = "*")
public class AvailabilityController {
    private AvailabilityService availabilityService;

    @PostMapping("/availabilities")
    public ResponseEntity<AvailabilityDto> createAvailability(@RequestBody AvailabilityCreationDto availabilityCreationDto) {
        AvailabilityDto savedAvailability = availabilityService.createAvailability(availabilityCreationDto);
        return new ResponseEntity<>(savedAvailability, HttpStatus.CREATED);
    }

    @GetMapping("/availabilities")
    public ResponseEntity<List<AvailabilityDto>> getAllAvailabilities() {
        List<AvailabilityDto> availabilities = availabilityService.getAllAvailabilities();

        return ResponseEntity.ok(availabilities);
    }

    @PatchMapping("/availabilities/{id}")
    public ResponseEntity<AvailabilityDto> updateAvailability(@PathVariable("id") Long availabilityId,
                                                    @RequestBody AvailabilityCreationDto availabilityCreationDto) {
        AvailabilityDto availabilityDto = availabilityService.updateAvailability(availabilityId, availabilityCreationDto);

        return ResponseEntity.ok(availabilityDto);
    }

    @DeleteMapping("/availabilities/{id}")
    public ResponseEntity<String> deleteAvailability(@PathVariable("id") Long availabilityId) {
        availabilityService.deleteAvailability(availabilityId);

        return ResponseEntity.ok("Availability deleted successfully");
    }
}
