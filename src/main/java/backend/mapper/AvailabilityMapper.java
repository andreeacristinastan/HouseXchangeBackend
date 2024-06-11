package backend.mapper;

import backend.dto.availability.AvailabilityCreationDto;
import backend.dto.availability.AvailabilityDto;
import backend.dto.feedback.FeedbackDto;
import backend.entity.Availability;
import backend.entity.Feedback;

public class AvailabilityMapper {
    public static Availability mapToAvailability(AvailabilityCreationDto availabilityCreationDto) {
        return new Availability(
                availabilityCreationDto.getStartDate(),
                availabilityCreationDto.getEndDate(),
                availabilityCreationDto.getUserId(),
                availabilityCreationDto.getPropertyId()
        );
    }

    public static AvailabilityDto mapToAvailabilityDto(Availability savedAvailability) {
        return new AvailabilityDto(
                savedAvailability.getId(),
                savedAvailability.getStartDate(),
                savedAvailability.getEndDate(),
                savedAvailability.getUserId(),
                savedAvailability.getPropertyId()
        );
    }
}
