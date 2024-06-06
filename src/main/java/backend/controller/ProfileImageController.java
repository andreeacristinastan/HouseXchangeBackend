package backend.controller;

import backend.dto.image.ImageCreationDto;
import backend.dto.image.ImageDto;
import backend.dto.profileImage.ProfileImageCreationDto;
import backend.dto.profileImage.ProfileImageDto;
import backend.dto.trip.TripUpdateDto;
import backend.repository.ProfileImageRepository;
import backend.repository.PropertyRepository;
import backend.service.ProfileImageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", allowPrivateNetwork = "true", allowedHeaders = "*")
public class ProfileImageController {
    ProfileImageService profileImageService;

    @PostMapping("/profile-images")
    public ResponseEntity<ProfileImageDto> createProfileImage(@RequestBody ProfileImageCreationDto profileImageCreationDto) {
        ProfileImageDto savedImage = profileImageService.createProfileImage(profileImageCreationDto);
        return new ResponseEntity<>(savedImage, HttpStatus.CREATED);
    }

    //Get image REST API
    @GetMapping("/profile-images/{image_id}")
    public ResponseEntity<ProfileImageDto> getImageById(@PathVariable("image_id") Long imageId) {
        ProfileImageDto imageDto = profileImageService.getImageById(imageId);

        return ResponseEntity.ok(imageDto);
    }

    //Get All images for a property REST API
    @PatchMapping("/users/{user_id}/profile-images/{profile_image_id}")
    public ResponseEntity<ProfileImageDto> updateProfileImageForAUser(@PathVariable("user_id") Long userId,@PathVariable("profile_image_id") Long profileImageId, @RequestBody ProfileImageCreationDto profileImageCreationDto) {
        ProfileImageDto image = profileImageService.updateProfileImageForAUser(profileImageId,userId, profileImageCreationDto);

        return ResponseEntity.ok(image);
    }

    @GetMapping("/users/{user_id}/profile-images/{image_id}")
    public ResponseEntity<ProfileImageDto> getImageByUser(@PathVariable("user_id") Long userId,
                                                       @PathVariable("image_id") Long imageId) {
        ProfileImageDto imageDto = profileImageService.getImageByUser(userId, imageId);

        return ResponseEntity.ok(imageDto);
    }

//    //Get all properties from database REST API
//    @GetMapping("/images")
//    public ResponseEntity<List<ImageDto>> getAllImages(@RequestParam(defaultValue = "0") Integer page,
//                                                       @RequestParam(defaultValue = "10") Integer size) {
//        List<ImageDto> images = profileImageService.getAllImages(page, size);
//
//        return ResponseEntity.ok(images);
//    }
}
