package webapp.housexchange.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import webapp.housexchange.dto.property.PropertyCreationDto;
import webapp.housexchange.dto.property.PropertyDto;
import webapp.housexchange.dto.property.PropertyUpdateDto;
import webapp.housexchange.entity.Property;
import webapp.housexchange.entity.User;
import webapp.housexchange.exceptions.*;
import webapp.housexchange.mapper.PropertyMapper;
import webapp.housexchange.repository.*;
import webapp.housexchange.security.AuthUtil;
import webapp.housexchange.service.PropertyService;
import webapp.housexchange.utils.CheckPermissionsHelper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PropertyServiceImpl implements PropertyService {
    private PropertyRepository propertyRepository;
    private UserRepository userRepository;
    private AuthUtil authUtil;
    private CheckPermissionsHelper checkPermissionsHelper;

    @Override
    public PropertyDto createProperty(Long userId, PropertyCreationDto propertyCreationDto) {
        if(propertyRepository.existsByNameAndCountryAndCityAndAddress(
                propertyCreationDto.getName(),
                propertyCreationDto.getCountry(),
                propertyCreationDto.getCity(),
                propertyCreationDto.getAddress()
        )) {
            throw new BadRequestException("Property already exists");
        }

        User user;

        try {
            user = userRepository.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

        checkPermissionsHelper.checkAuth(user.getEmail(), authUtil);
        checkPermissionsHelper.checkUser(user.getRole(), authUtil);

        Property property = PropertyMapper.mapToProperty(propertyCreationDto, user);
        property.setUser(user);

        Property savedProperty;
        try {
            savedProperty = propertyRepository.save(property);
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

        return PropertyMapper.mapToPropertyDto(savedProperty);
    }

    @Override
    public List<PropertyDto> getAllProperties(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Property> propertyPage = propertyRepository.findAll(pageable);

        return propertyPage.stream().map(PropertyMapper::mapToPropertyDto)
                .collect(Collectors.toList());
    }

    @Override
    public PropertyDto getPropertyById(Long propertyId) {
        Property property;
        try {
            property = propertyRepository.findById(propertyId)
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Property doesn't exists with given id: " + propertyId));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }
        return PropertyMapper.mapToPropertyDto(property);
    }

    @Override
    public PropertyDto updateProperty(Long propertyId, PropertyUpdateDto updatedProperty) {
        Property property;
        try {
            property = propertyRepository.findById(propertyId).orElseThrow(
                    () -> new ResourceNotFoundException("Property is not exists with given id: " + propertyId)
            );
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
        checkPermissionsHelper.checkUser(user.getRole(), authUtil);

        property.setAccessibility(updatedProperty.getAccessibility());
        property.setAddress(updatedProperty.getAddress());
        property.setAllowedPet(updatedProperty.getAllowedPet());
        property.setCity(updatedProperty.getCity());
        property.setDistance(updatedProperty.getDistance());
        property.setNumberOfRooms(updatedProperty.getNumberOfRooms());
        property.setName(updatedProperty.getName());
        property.setCountry(updatedProperty.getCountry());

        Property updatesPropertyObj;

        try {
            updatesPropertyObj = propertyRepository.save(property);
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

        return PropertyMapper.mapToPropertyDto(updatesPropertyObj);
    }

    @Override
    public void deleteProperty(Long propertyId) {
        Property property;

        try {
            property = propertyRepository.findById(propertyId).orElseThrow(
                    () -> new ResourceNotFoundException("Property is not exists with given id: " + propertyId));
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
        checkPermissionsHelper.checkUser(user.getRole(), authUtil);

        try {
            propertyRepository.deleteById(propertyId);
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }
    }

    @Override
    public PropertyDto updatePropertyByUserId(Long userId, Long propertyId, PropertyUpdateDto updatedProperty) {
        User user;

        try {
            user = userRepository.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

        Property property = null;
        for(Property prop : user.getProperties()) {
            if(prop.getId() == propertyId) {
                property = prop;
                break;
            }
        }
        if(property == null) {
            throw new ResourceNotFoundException("Property not found");
        }

        if(!property.getUser().equals(user)) {
            throw new IllegalArgumentException("Property does not belong to the user");
        }

        checkPermissionsHelper.checkAuth(user.getEmail(), authUtil);

        property.setAccessibility(updatedProperty.getAccessibility());
        property.setAddress(updatedProperty.getAddress());
        property.setAllowedPet(updatedProperty.getAllowedPet());
        property.setCity(updatedProperty.getCity());
        property.setDistance(updatedProperty.getDistance());
        property.setNumberOfRooms(updatedProperty.getNumberOfRooms());
        property.setName(updatedProperty.getName());
        property.setCountry(updatedProperty.getCountry());

        Property updatedPropertyObj;

        try {
            updatedPropertyObj = propertyRepository.save(property);
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }
        return PropertyMapper.mapToPropertyDto(updatedPropertyObj);
    }

    @Override
    public void deletePropertyByUser(Long userId, Long propertyId) {
        User user;

        try {
            user = userRepository.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

        Property property;

        try {
            property = propertyRepository.findById(propertyId)
                    .orElseThrow(() -> new ResourceNotFoundException("Property not found"));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

        if(!property.getUser().equals(user)) {
            throw new IllegalArgumentException("Property does not belong to the user");
        }

        checkPermissionsHelper.checkAuth(user.getEmail(), authUtil);

        propertyRepository.delete(property);

    }

}
