package backend.controller;

import backend.dto.image.ImageCreationDto;
import backend.dto.image.ImageDto;
import backend.dto.property.PropertyCreationDto;
import backend.dto.property.PropertyDto;
import backend.dto.trip.TripDto;
import backend.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", allowPrivateNetwork = "true", allowedHeaders = "*")
public class ImageController {

    private ImageService imageService;

    @PostMapping("/images")
    public ResponseEntity<ImageDto> createImage(@RequestBody ImageCreationDto imageCreationDto) {
        ImageDto savedImage = imageService.createImage(imageCreationDto);
        return new ResponseEntity<>(savedImage, HttpStatus.CREATED);
    }

    //Get image REST API
    @GetMapping("/images/{image_id}")
    public ResponseEntity<ImageDto> getImageById(@PathVariable("image_id") Long imageId) {
        ImageDto imageDto = imageService.getImageById(imageId);

        return ResponseEntity.ok(imageDto);
    }

    //Get All images for a property REST API
    @GetMapping("/properties/{property_id}/images")
    public ResponseEntity<List<ImageDto>> getAllImagesByProperty(@RequestParam(defaultValue = "0") Integer page,
                                                           @RequestParam(defaultValue = "10") Integer size,
                                                           @PathVariable("property_id") Long propertyId) {
        List<ImageDto> images = imageService.getAllImagesByProperty(propertyId, page, size);

        return ResponseEntity.ok(images);
    }

    @GetMapping("/properties/{property_id}/images/{image_id}")
    public ResponseEntity<ImageDto> getImageByProperty(@PathVariable("property_id") Long propertyId,
                                                 @PathVariable("image_id") Long imageId) {
        ImageDto imageDto = imageService.getImageByProperty(propertyId, imageId);

        return ResponseEntity.ok(imageDto);
    }

    //Get all properties from database REST API
    @GetMapping("/images")
    public ResponseEntity<List<ImageDto>> getAllImages(@RequestParam(defaultValue = "0") Integer page,
                                                              @RequestParam(defaultValue = "10") Integer size) {
        List<ImageDto> images = imageService.getAllImages(page, size);

        return ResponseEntity.ok(images);
    }

    @DeleteMapping("/properties/{property_id}/images")
    public ResponseEntity<String> deleteAllImagesByProperty(@PathVariable("property_id") Long propertyId) {
        imageService.deleteAllImagesByProperty(propertyId);

        return ResponseEntity.ok("Images deleted successfully!");
    }
}
