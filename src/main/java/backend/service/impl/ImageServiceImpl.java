package backend.service.impl;

import backend.dto.image.ImageCreationDto;
import backend.dto.image.ImageDto;
import backend.entity.*;
import backend.exceptions.DatabaseException;
import backend.exceptions.ResourceNotFoundException;
import backend.mapper.FeedbackMapper;
import backend.mapper.ImageMapper;
import backend.mapper.PropertyMapper;
import backend.mapper.TripMapper;
import backend.repository.ImageRepository;
import backend.repository.PropertyRepository;
import backend.repository.UserRepository;
import backend.security.AuthUtil;
import backend.service.ImageService;
import backend.utils.CheckPermissionsHelper;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ImageServiceImpl implements ImageService {
    private PropertyRepository propertyRepository;
    private UserRepository userRepository;
    private ImageRepository imageRepository;
    private AuthUtil authUtil;
    private CheckPermissionsHelper checkPermissionsHelper;

    @Override
    public ImageDto createImage(ImageCreationDto imageCreationDto) {

            Property property;
            try {
                property = propertyRepository.findById(imageCreationDto.getPropertyId())
                        .orElseThrow(() -> new ResourceNotFoundException("Property not found"));
            } catch (DataAccessException exception) {
                throw new DatabaseException("Exception occurred while accessing the database", exception);
            }

        checkPermissionsHelper.checkAuth(property.getUser().getUsername(), authUtil);

        Image image = ImageMapper.mapToImage(imageCreationDto, property);
        image.setProperty(property);

        Image savedImage;
        try {
            savedImage = imageRepository.save(image);
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }
        return ImageMapper.mapToImageDto(savedImage);
    }

    @Override
    public ImageDto getImageById(Long imageId) {
        Image image;
        try {
            image = imageRepository.findById(imageId)
                    .orElseThrow(() -> new ResourceNotFoundException("Image not found"));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

//        checkPermissionsHelper.checkAuth(trip.getUser().getEmail(), authUtil);
        return ImageMapper.mapToImageDto(image);
    }

    @Override
    public List<ImageDto> getAllImagesByProperty(Long propertyId, Integer page, Integer size) {
        Property property;
        try {
            property = propertyRepository.findById(propertyId)
                    .orElseThrow(() -> new ResourceNotFoundException("Property not found"));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

        Pageable pageable = PageRequest.of(page, size);
        Page<Image> imagePage = imageRepository.findByProperty(property, pageable);

        return imagePage.stream().map(ImageMapper::mapToImageDto)
                .collect(Collectors.toList());
    }

    @Override
    public ImageDto getImageByProperty(Long propertyId, Long imageId) {
        Property property;

        try {
            property = propertyRepository.findById(propertyId)
                    .orElseThrow(() -> new ResourceNotFoundException("Property not found"));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

        Image image;
        try {
            image = imageRepository.findById(imageId)
                    .orElseThrow(() -> new ResourceNotFoundException("Image not found"));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

        if(property.getImages().stream().anyMatch(currImage -> currImage.getId() == imageId)) {
            return ImageMapper.mapToImageDto(image);
        } else {
            throw new ResourceNotFoundException("Image not found for the given property");
        }
    }

    @Override
    public List<ImageDto> getAllImages(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Image> imagePage = imageRepository.findAll(pageable);

        return imagePage.stream().map(ImageMapper::mapToImageDto)
                .collect(Collectors.toList());
    }
}
