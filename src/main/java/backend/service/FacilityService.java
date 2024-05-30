package backend.service;

import backend.dto.facility.FacilityCreationDto;
import backend.dto.facility.FacilityDto;
import backend.dto.room.RoomDto;

import java.util.List;

public interface FacilityService {
    FacilityDto createFacility(Long propertyId, FacilityCreationDto facilityCreationDto);

//    List<FacilityDto> getAllFacilities(Integer page, Integer size, Long propertyId);

    void deleteFacility(Long propertyId, Long facilityId);

    FacilityDto getFacilityById(Long propertyId, Long facilityId);
}
