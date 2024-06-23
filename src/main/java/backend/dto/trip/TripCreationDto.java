package backend.dto.trip;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@Getter
public class TripCreationDto {
    private Integer numberOfPersons;
    private String destination;
    private Integer minRange;
    private Integer maxRange;
    private Date checkInDate;
    private Date checkOutDate;
    private Long userId;
    private Long propertyId;
}
