package backend.service;

import backend.dto.image.ImageCreationDto;
import backend.dto.image.ImageDto;

import java.util.List;

public interface ImageService {
    ImageDto createImage(ImageCreationDto imageCreationDto);

    ImageDto getImageById(Long imageId);

    List<ImageDto> getAllImagesByProperty(Long propertyId, Integer page, Integer size);

    ImageDto getImageByProperty(Long propertyId, Long imageId);

    List<ImageDto> getAllImages(Integer page, Integer size);

    void deleteAllImagesByProperty(Long propertyId);
}
