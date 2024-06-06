package backend.mapper;

import backend.dto.image.ImageDto;
import backend.dto.profileImage.ProfileImageCreationDto;
import backend.dto.profileImage.ProfileImageDto;
import backend.entity.Image;
import backend.entity.ProfileImage;
import backend.entity.User;

public class ProfileImageMapper {
    public static ProfileImage mapToImage(ProfileImageCreationDto profileImageCreationDto, User user) {
        return new ProfileImage(
                profileImageCreationDto.getUrl(),
                user
        );
    }

    public static ProfileImageDto mapToImageDto(ProfileImage savedImage) {
        if(savedImage == null) {
            return null;
        }
        return new ProfileImageDto(
                savedImage.getId(),
                savedImage.getUrl(),
                savedImage.getUser().getId()

        );
    }
}
