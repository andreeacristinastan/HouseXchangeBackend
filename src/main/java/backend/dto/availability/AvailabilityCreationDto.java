package backend.dto.availability;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class AvailabilityCreationDto {

    private String startDate;

    private String endDate;

    private Long userId;

    private Long propertyId;
}
