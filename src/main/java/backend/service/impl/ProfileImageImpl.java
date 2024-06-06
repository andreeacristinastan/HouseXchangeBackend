package backend.service.impl;

import backend.dto.profileImage.ProfileImageCreationDto;
import backend.dto.profileImage.ProfileImageDto;
import backend.entity.ProfileImage;
import backend.entity.Trip;
import backend.entity.User;
import backend.exceptions.BadRequestException;
import backend.exceptions.DatabaseException;
import backend.exceptions.ResourceNotFoundException;
import backend.mapper.ProfileImageMapper;
import backend.mapper.TripMapper;
import backend.repository.ProfileImageRepository;
import backend.repository.UserRepository;
import backend.security.AuthUtil;
import backend.service.ProfileImageService;
import backend.utils.CheckPermissionsHelper;
import backend.utils.LoggerHelper;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class ProfileImageImpl implements ProfileImageService {
    private UserRepository userRepository;
    private ProfileImageRepository profileImageRepository;
    private AuthUtil authUtil;
    private CheckPermissionsHelper checkPermissionsHelper;
    @Override
    public ProfileImageDto createProfileImage(ProfileImageCreationDto profileImageCreationDto) {
        User user;
        try {
            user = userRepository.findById(profileImageCreationDto.getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

        checkPermissionsHelper.checkAuth(user.getUsername(), authUtil);

        ProfileImage image = ProfileImageMapper.mapToImage(profileImageCreationDto, user);
        image.setUser(user);

        ProfileImage savedImage;
        try {
            savedImage = profileImageRepository.save(image);
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }
        return ProfileImageMapper.mapToImageDto(savedImage);
    }

    @Override
    public ProfileImageDto getImageById(Long imageId) {
        ProfileImage image;
        try {
            image = profileImageRepository.findById(imageId)
                    .orElseThrow(() -> new ResourceNotFoundException("Image not found"));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

//        checkPermissionsHelper.checkAuth(trip.getUser().getEmail(), authUtil);
        return ProfileImageMapper.mapToImageDto(image);
    }

    @Override
    public ProfileImageDto getImageByUser(Long userId, Long imageId) {
        User user;

        try {
            user = userRepository.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

        ProfileImage image;
        try {
            image = profileImageRepository.findById(imageId)
                    .orElseThrow(() -> new ResourceNotFoundException("Image not found"));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

        if(user.getProfileImage().getId() == imageId) {
            return ProfileImageMapper.mapToImageDto(image);
        } else {
            throw new ResourceNotFoundException("Image not found for the given property");
        }
    }

    @Override
    public ProfileImageDto updateProfileImageForAUser(Long id, Long userId, ProfileImageCreationDto profileImageCreationDto) {
        User user;
        LoggerHelper.LOGGER.info(String.valueOf(userId));
        try {
            user = userRepository.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

        checkPermissionsHelper.checkAuth(user.getUsername(), authUtil);

        ProfileImage image;
        try {
            image = profileImageRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Image not found"));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }



        if(profileImageCreationDto.getUrl() != null) {
            image.setUrl(profileImageCreationDto.getUrl());
        }

            ProfileImage updatedObj;
            try {
                updatedObj = profileImageRepository.save(image);
            } catch (DataAccessException exception) {
                throw new DatabaseException("Exception occurred while accessing the database", exception);
            }
            return ProfileImageMapper.mapToImageDto(updatedObj);

    }
}
