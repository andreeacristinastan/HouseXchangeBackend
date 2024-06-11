package backend.dto.availability;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class AvailabilityDto {
    private Long id;
    private String startDate;
    private String endDate;
    private Long userId;
    private Long propertyId;
}
