package webapp.housexchange.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import webapp.housexchange.dto.amenity.AmenityCreationDto;
import webapp.housexchange.dto.amenity.AmenityDto;
import webapp.housexchange.dto.amenity.AmenityUpdateDto;
import webapp.housexchange.entity.Amenity;
import webapp.housexchange.entity.Property;
import webapp.housexchange.entity.User;
import webapp.housexchange.exceptions.BadRequestException;
import webapp.housexchange.exceptions.DatabaseException;
import webapp.housexchange.exceptions.ResourceNotFoundException;
import webapp.housexchange.mapper.AmenityMapper;
import webapp.housexchange.repository.AmenityRepository;
import webapp.housexchange.repository.PropertyRepository;
import webapp.housexchange.repository.UserRepository;
import webapp.housexchange.security.AuthUtil;
import webapp.housexchange.service.AmenityService;
import webapp.housexchange.utils.CheckPermissionsHelper;

@Service
@AllArgsConstructor
public class AmenityServiceImpl implements AmenityService {
    private PropertyRepository propertyRepository;
    private AmenityRepository amenityRepository;
    private UserRepository userRepository;
    private AuthUtil authUtil;
    private CheckPermissionsHelper checkPermissionsHelper;

    @Override
    public AmenityDto createAmenity(AmenityCreationDto amenityCreationDto) {
        Property property;
        try {
            property = propertyRepository.findById(amenityCreationDto.getPropertyId()).orElseThrow(
                    () -> new ResourceNotFoundException("Property not found"));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

        if(property.getAmenity() != null) {
            throw new BadRequestException("Amenity already exists for the given property");
        }

        User user;
        try {
            user = userRepository.findById(property.getUser().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

        checkPermissionsHelper.checkAuth(user.getEmail(), authUtil);

        Amenity amenity = AmenityMapper.mapToAmenity(amenityCreationDto, property);
        amenity.setProperty(property);

        Amenity savedAmenity;

        try {
            savedAmenity = amenityRepository.save(amenity);
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

        return AmenityMapper.mapToAmenityDto(savedAmenity);
    }

    @Override
    public AmenityDto getAmenityById(Long propertyId) {
        Property property;
        try {
            property = propertyRepository.findById(propertyId).orElseThrow(
                    () -> new ResourceNotFoundException("Property not found"));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

        if(property.getAmenity() != null) {
            return AmenityMapper.mapToAmenityDto(property.getAmenity());
        } else {
            throw new ResourceNotFoundException("Amenity not found");
        }

    }

    @Override
    public AmenityDto updateAmenity(Long propertyId, AmenityUpdateDto amenityUpdateDto) {
        Property property;

        try {
            property = propertyRepository.findById(propertyId).orElseThrow(
                    () -> new ResourceNotFoundException("Property not found"));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

        User user;
        try {
            user = userRepository.findById(property.getUser().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

        checkPermissionsHelper.checkAuth(user.getEmail(), authUtil);

        if(property.getAmenity() != null) {
            Amenity amenity = property.getAmenity();

            amenity.setGym(amenityUpdateDto.getGym());
            amenity.setSwimmingPool(amenityUpdateDto.getSwimmingPool());
            amenity.setGarden(amenityUpdateDto.getGarden());
            amenity.setParking(amenityUpdateDto.getParking());
            amenity.setWireless(amenityUpdateDto.getWireless());
            amenity.setBikes(amenityUpdateDto.getBikes());
            amenity.setKidsZone(amenityUpdateDto.getKidsZone());

            Amenity updatedObj;

            try {
                updatedObj = amenityRepository.save(amenity);
            } catch (DataAccessException exception) {
                throw new DatabaseException("Exception occurred while accessing the database", exception);
            }

            return AmenityMapper.mapToAmenityDto(updatedObj);
        } else {
            throw new ResourceNotFoundException("Amenity not found for the given property");
        }
    }

    @Override
    public void deleteAmenity(Long propertyId) {
        Property property;
        try {
            property = propertyRepository.findById(propertyId).orElseThrow(
                    () -> new ResourceNotFoundException("Property not found"));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

        User user;
        try {
            user = userRepository.findById(property.getUser().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

        checkPermissionsHelper.checkAuth(user.getEmail(), authUtil);

        if (property.getAmenity() != null) {
            property.setAmenity(null);

            try {
                propertyRepository.save(property);
            } catch (DataAccessException exception) {
                throw new DatabaseException("Exception occurred while accessing the database", exception);
            }

        } else {
            throw new BadRequestException("Cannot delete an amenity that doesn't exists");
        }

    }
}
