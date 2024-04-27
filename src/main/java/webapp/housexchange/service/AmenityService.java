package webapp.housexchange.service;

import webapp.housexchange.dto.amenity.AmenityCreationDto;
import webapp.housexchange.dto.amenity.AmenityDto;
import webapp.housexchange.dto.amenity.AmenityUpdateDto;

public interface AmenityService {
    AmenityDto createAmenity(AmenityCreationDto amenityCreationDto);

    AmenityDto getAmenityById(Long propertyId);

    AmenityDto updateAmenity(Long propertyId, AmenityUpdateDto amenityUpdateDto);

    void deleteAmenity(Long propertyId);
}
