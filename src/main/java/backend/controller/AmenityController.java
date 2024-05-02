package backend.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import backend.dto.amenity.AmenityCreationDto;
import backend.dto.amenity.AmenityDto;
import backend.dto.amenity.AmenityUpdateDto;
import backend.service.AmenityService;

@RestController
@AllArgsConstructor
//@RequestMapping("/api/amenities")
public class AmenityController {
    private AmenityService amenityService;

    //Add amenity REST API
    @PostMapping("/api/amenities")
    public ResponseEntity<AmenityDto> createAmenity(@RequestBody AmenityCreationDto amenityCreationDto) {
        AmenityDto savedAmenity = amenityService.createAmenity(amenityCreationDto);
        return new ResponseEntity<>(savedAmenity, HttpStatus.CREATED);
    }

    //Get amenity for a property REST API
    @GetMapping("/api/properties/{property_id}/amenities")
    public ResponseEntity<AmenityDto> getAmenityById(@PathVariable("property_id") Long propertyId) {
        AmenityDto amenityDto = amenityService.getAmenityById(propertyId);

        return ResponseEntity.ok(amenityDto);
    }

    // Update amenity REST API
    @PutMapping("/api/properties/{property_id}/amenities")
    public ResponseEntity<AmenityDto> updateAmenity(@PathVariable("property_id") Long propertyId,
                                              @RequestBody AmenityUpdateDto amenityUpdateDto) {
        AmenityDto amenityDto = amenityService.updateAmenity(propertyId, amenityUpdateDto);

        return ResponseEntity.ok(amenityDto);
    }

    //Delete amenity REST API
    @DeleteMapping("/api/properties/{property_id}/amenities")
    public ResponseEntity<String> deleteAmenity(@PathVariable("property_id") Long propertyId) {
        amenityService.deleteAmenity(propertyId);

        return ResponseEntity.ok("Amenity deleted successfully");
    }
}
