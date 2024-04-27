package webapp.housexchange.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webapp.housexchange.dto.facility.FacilityCreationDto;
import webapp.housexchange.dto.facility.FacilityDto;
import webapp.housexchange.dto.room.RoomDto;
import webapp.housexchange.service.FacilityService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/properties")
public class FacilityController {
    private FacilityService facilityService;

    //Add a facility to a specific room
    @PostMapping("{property_id}/rooms/{room_id}/facilities")
    public ResponseEntity<RoomDto> createFacility(@PathVariable("property_id") Long propertyId,
                                                  @PathVariable("room_id") Long roomId,
                                                  @RequestBody FacilityCreationDto facilityCreationDto) {
        RoomDto savedFacility = facilityService.createFacility(propertyId, roomId, facilityCreationDto);
        return new ResponseEntity<>(savedFacility, HttpStatus.CREATED);
    }

    //Get all facilities from specific room REST API
    @GetMapping("{property_id}/rooms/{room_id}/facilities")
    public ResponseEntity<List<FacilityDto>> getAllFacilities(@RequestParam(defaultValue = "0") Integer page,
                                                              @RequestParam(defaultValue = "10") Integer size,
                                                              @PathVariable("property_id") Long propertyId,
                                                              @PathVariable("room_id") Long roomId) {
        List<FacilityDto> facilities = facilityService.getAllFacilities(page, size, propertyId, roomId);

        return ResponseEntity.ok(facilities);
    }

    //Delete a facility from a specific room REST API
    @DeleteMapping("{property_id}/rooms/{room_id}/facilities/{facility_id}")
    public ResponseEntity<String> deleteFacility(@PathVariable("property_id") Long propertyId,
                                                 @PathVariable("room_id") Long roomId,
                                                 @PathVariable("facility_id") Long facilityId) {
        facilityService.deleteFacility(propertyId, roomId, facilityId);
        return ResponseEntity.ok("Facility deleted successfully");
    }

    //Get a facility by id REST API
    @GetMapping("{property_id}/rooms/{room_id}/facilities/{facility_id}")
    public ResponseEntity<FacilityDto> getFacilityById(@PathVariable("property_id") Long propertyId,
                                                       @PathVariable("room_id") Long roomId,
                                                       @PathVariable("facility_id") Long facilityId) {
        FacilityDto facilityDto = facilityService.getFacilityById(propertyId, roomId, facilityId);

        return ResponseEntity.ok(facilityDto);
    }
}
