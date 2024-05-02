package backend.service;

import backend.dto.amenity.AmenityCreationDto;
import backend.dto.amenity.AmenityDto;
import backend.dto.amenity.AmenityUpdateDto;

public interface AmenityService {
    AmenityDto createAmenity(AmenityCreationDto amenityCreationDto);

    AmenityDto getAmenityById(Long propertyId);

    AmenityDto updateAmenity(Long propertyId, AmenityUpdateDto amenityUpdateDto);

    void deleteAmenity(Long propertyId);
}
