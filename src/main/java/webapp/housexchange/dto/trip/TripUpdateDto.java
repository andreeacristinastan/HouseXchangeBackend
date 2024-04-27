package webapp.housexchange.dto.trip;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class TripUpdateDto {
    private Integer numberOfPersons;
    private Integer minRange;
    private Integer maxRange;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

}
