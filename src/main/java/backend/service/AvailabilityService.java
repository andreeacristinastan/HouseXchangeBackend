package backend.service;

import backend.dto.availability.AvailabilityCreationDto;
import backend.dto.availability.AvailabilityDto;

import java.util.List;

public interface AvailabilityService {
    AvailabilityDto createAvailability(AvailabilityCreationDto availabilityCreationDto);

    List<AvailabilityDto> getAllAvailabilities();

    AvailabilityDto updateAvailability(Long availabilityId, AvailabilityCreationDto availabilityCreationDto);

    void deleteAvailability(Long availabilityId);
}
