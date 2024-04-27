package webapp.housexchange.service;

import webapp.housexchange.dto.property.PropertyCreationDto;
import webapp.housexchange.dto.property.PropertyDto;
import webapp.housexchange.dto.property.PropertyUpdateDto;

import java.util.List;

public interface PropertyService {
    PropertyDto createProperty(Long userId, PropertyCreationDto propertyCreationDto);
    List<PropertyDto> getAllProperties(Integer page, Integer size);

    PropertyDto getPropertyById(Long propertyId);

    PropertyDto updateProperty(Long propertyId, PropertyUpdateDto updatedProperty);

    void deleteProperty(Long propertyId);

    PropertyDto updatePropertyByUserId(Long userId, Long propertyId, PropertyUpdateDto propertyUpdateDto);

    void deletePropertyByUser(Long userId, Long propertyId);
}
