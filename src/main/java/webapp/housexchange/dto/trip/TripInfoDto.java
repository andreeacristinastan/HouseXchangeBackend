package webapp.housexchange.dto.trip;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class TripInfoDto {
    private Long id;
    private Integer numberOfPersons;
    private String destination;
    private Integer minRange;
    private Integer maxRange;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private Long userId;
    private Long propertyId;
}
