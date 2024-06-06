package backend.mapper;

import backend.dto.image.ImageCreationDto;
import backend.dto.image.ImageDto;
import backend.dto.trip.TripDto;
import backend.entity.*;

public class ImageMapper {
    public static Image mapToImage(ImageCreationDto imageCreationDto, Property property) {
        return new Image(
                imageCreationDto.getUrl(),
                property

                );
    }

    public static ImageDto mapToImageDto(Image image) {

        return new ImageDto(
                image.getId(),
                image.getUrl(),
                image.getProperty().getId()

        );
    }
}
