package backend.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import backend.dto.property.PropertyCreationDto;
import backend.dto.property.PropertyDto;
import backend.dto.property.PropertyUpdateDto;
import backend.service.PropertyService;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class PropertyController {
        private PropertyService propertyService;

    //Add property to a specific user REST API
    @PostMapping("/properties")
    public ResponseEntity<PropertyDto> createProperty(@RequestBody PropertyCreationDto propertyCreationDto) {
        PropertyDto savedProperty = propertyService.createProperty(propertyCreationDto.getUserId(), propertyCreationDto);
        return new ResponseEntity<>(savedProperty, HttpStatus.CREATED);
    }

    //Get all properties from database REST API
    @GetMapping("/properties")
    public ResponseEntity<List<PropertyDto>> getAllProperties(@RequestParam(defaultValue = "0") Integer page,
                                                              @RequestParam(defaultValue = "10") Integer size) {
        List<PropertyDto> properties = propertyService.getAllProperties(page, size);

        return ResponseEntity.ok(properties);
    }

    //Get property by id REST API
    @GetMapping("/properties/{propertyId}")
    public ResponseEntity<PropertyDto> getPropertyByUserId(@PathVariable("propertyId") Long propertyId) {
        PropertyDto propertyDto = propertyService.getPropertyById(propertyId);

        return ResponseEntity.ok(propertyDto);
    }

    // Update property REST API
//    @PutMapping("/properties/{propertyId}")
//    public ResponseEntity<PropertyDto> updateProperty(@PathVariable("propertyId") Long propertyId,
//                                              @RequestBody PropertyUpdateDto updatedProperty) {
//        PropertyDto propertyDto = propertyService.updateProperty(propertyId, updatedProperty);
//
//        return ResponseEntity.ok(propertyDto);
//    }

    //Delete property REST API
//    @DeleteMapping("/properties/{propertyId}")
//    public ResponseEntity<String> deleteProperty(@PathVariable("propertyId") Long propertyId) {
//        propertyService.deleteProperty(propertyId);
//
//        return ResponseEntity.ok("Property deleted successfully");
//    }

    //Update properties by user REST API
    @PutMapping("/users/{userId}/properties/{propertyId}")
    public ResponseEntity<PropertyDto> updatePropertyByUserId(@PathVariable("userId") Long userId,
                                                                    @PathVariable("propertyId") Long propertyId,
                                                                    @RequestBody PropertyUpdateDto propertyUpdateDto) {
        PropertyDto property = propertyService.updatePropertyByUserId(userId, propertyId, propertyUpdateDto);
        return ResponseEntity.ok(property);
    }

    //Delete property by user REST API
    @DeleteMapping("/users/{userId}/properties/{propertyId}")
    public ResponseEntity<String> deletePropertyByUser(@PathVariable("userId") Long userId,
                                                       @PathVariable("propertyId") Long propertyId) {
        propertyService.deletePropertyByUser(userId, propertyId);

        return ResponseEntity.ok("Property deleted successfully");
    }

}
