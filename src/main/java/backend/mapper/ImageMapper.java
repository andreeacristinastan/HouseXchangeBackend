package backend.mapper;

import backend.dto.image.ImageCreationDto;
import backend.dto.image.ImageDto;
import backend.dto.trip.TripDto;
import backend.entity.Feedback;
import backend.entity.Image;
import backend.entity.Property;
import backend.entity.Trip;

public class ImageMapper {
    public static Image mapToImage(ImageCreationDto imageCreationDto, Property property) {
        return new Image(
                imageCreationDto.getPublicId(),
                property
        );
    }

    public static ImageDto mapToImageDto(Image image) {

        return new ImageDto(
                image.getId(),
                image.getPublicId(),
                image.getProperty().getId()

        );
    }
}
