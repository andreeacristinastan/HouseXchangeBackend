package backend.dto.trip;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TripInfoPropertyDto {
    private Long id;
    private Integer numberOfPersons;
    private String destination;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private Long userId;

}
