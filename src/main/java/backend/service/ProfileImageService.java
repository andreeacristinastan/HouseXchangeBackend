package backend.service;

import backend.dto.profileImage.ProfileImageCreationDto;
import backend.dto.profileImage.ProfileImageDto;

public interface ProfileImageService {
    ProfileImageDto createProfileImage(ProfileImageCreationDto profileImageCreationDto);

    ProfileImageDto getImageById(Long imageId);

    ProfileImageDto getImageByUser(Long userId, Long imageId);

    ProfileImageDto updateProfileImageForAUser(Long id, Long userId, ProfileImageCreationDto profileImageCreationDto);
}
