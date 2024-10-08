package backend.service.impl;

import backend.dto.user.UserOutputDto;
import backend.entity.*;
import backend.mapper.*;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import backend.dto.property.PropertyCreationDto;
import backend.dto.property.PropertyDto;
import backend.dto.property.PropertyUpdateDto;
import backend.exceptions.BadRequestException;
import backend.exceptions.DatabaseException;
import backend.exceptions.ResourceNotFoundException;
import backend.repository.PropertyRepository;
import backend.repository.UserRepository;
import backend.security.AuthUtil;
import backend.service.PropertyService;
import backend.utils.CheckPermissionsHelper;

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

        checkPermissionsHelper.checkAuth(user.getUsername(), authUtil);
        checkPermissionsHelper.checkUser(user.getRole(), authUtil);

        Property property = PropertyMapper.mapToProperty(propertyCreationDto, user);
        Amenity amenity = AmenityMapper.mapToAmenity(propertyCreationDto.getAmenities(), property);
        property.setAmenity(amenity);

        Facility facility = FacilityMapper.mapToFacility(propertyCreationDto.getFacilities(), property);
        property.setFacility(facility);

        Meal meal = MealMapper.mapToMeal(propertyCreationDto.getMeals(), property);
        property.setMeal(meal);

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
    public Page<PropertyDto> getAllPropertiesPageable(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Property> propertyPage = propertyRepository.findAll(pageable);
        List<PropertyDto> properties = propertyPage.stream()
                .map(PropertyMapper::mapToPropertyDto)
                .collect(Collectors.toList());

        return new PageImpl<>(properties, pageable, propertyPage.getTotalElements());
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


        checkPermissionsHelper.checkAuth(user.getUsername(), authUtil);

        Amenity amenity = property.getAmenity();
        amenity.setBikes(updatedProperty.getAmenities().getBikes());
        amenity.setGarden(updatedProperty.getAmenities().getGarden());
        amenity.setDisabilitiesFriendly(updatedProperty.getAmenities().getDisabilitiesFriendly());
        amenity.setGym(updatedProperty.getAmenities().getGym());
        amenity.setKidsZone(updatedProperty.getAmenities().getKidsZone());
        amenity.setParking(updatedProperty.getAmenities().getParking());
        amenity.setPetsFriendly(updatedProperty.getAmenities().getPetsFriendly());
        amenity.setWifi(updatedProperty.getAmenities().getWifi());
        amenity.setSwimmingPool(updatedProperty.getAmenities().getSwimmingPool());

        property.setAmenity(amenity);

        Facility facility = property.getFacility();
        facility.setAirConditioning(updatedProperty.getFacilities().getAirConditioning());
        facility.setBalcony(updatedProperty.getFacilities().getBalcony());
        facility.setTowel(updatedProperty.getFacilities().getTowel());
        facility.setTv(updatedProperty.getFacilities().getTv());
        property.setFacility(facility);


        if(updatedProperty.getNumberOfRooms() != 0) {
            property.setNumberOfRooms(updatedProperty.getNumberOfRooms());
        }

        if(updatedProperty.getNumberOfBathrooms() != 0) {
            property.setNumberOfBathrooms(updatedProperty.getNumberOfBathrooms());
        }

        if(updatedProperty.getName().length() != 0) {
            property.setName(updatedProperty.getName());

        }

        if(updatedProperty.getPrice() != 0) {
            property.setPrice(updatedProperty.getPrice());
        }

        if(updatedProperty.getPropertyDescription().length() != 0) {
            property.setPropertyDescription(updatedProperty.getPropertyDescription());

        }
        Property updatedPropertyObj;

        try {
            updatedPropertyObj = propertyRepository.save(property);
            facility.setProperty(updatedPropertyObj);
            amenity.setProperty(updatedPropertyObj);
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

        checkPermissionsHelper.checkAuth(user.getUsername(), authUtil);

        propertyRepository.delete(property);

    }

    @Override
    public List<PropertyDto> getAllProperties() {
        List<Property> properties = propertyRepository.findAll();
//        System.out.println("get all");
        return properties.stream().map(PropertyMapper::mapToPropertyDto)
                .collect(Collectors.toList());
    }

}
