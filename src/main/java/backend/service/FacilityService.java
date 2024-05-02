package backend.service;

import backend.dto.facility.FacilityCreationDto;
import backend.dto.facility.FacilityDto;
import backend.dto.room.RoomDto;

import java.util.List;

public interface FacilityService {
    RoomDto createFacility(Long propertyId, Long roomId, FacilityCreationDto facilityCreationDto);

    List<FacilityDto> getAllFacilities(Integer page, Integer size, Long propertyId, Long roomId);

    void deleteFacility(Long propertyId, Long roomId, Long facilityId);

    FacilityDto getFacilityById(Long propertyId, Long roomId, Long facilityId);
}
