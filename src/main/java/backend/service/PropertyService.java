package backend.service;

import backend.dto.property.PropertyCreationDto;
import backend.dto.property.PropertyDto;
import backend.dto.property.PropertyUpdateDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PropertyService {
    PropertyDto createProperty(Long userId, PropertyCreationDto propertyCreationDto);
    Page<PropertyDto> getAllProperties(Integer page, Integer size);

    PropertyDto getPropertyById(Long propertyId);

//    PropertyDto updateProperty(Long propertyId, PropertyUpdateDto updatedProperty);

//    void deleteProperty(Long propertyId);

    PropertyDto updatePropertyByUserId(Long userId, Long propertyId, PropertyUpdateDto propertyUpdateDto);

    void deletePropertyByUser(Long userId, Long propertyId);
}
