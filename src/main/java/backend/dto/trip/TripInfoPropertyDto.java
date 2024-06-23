package backend.dto.trip;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TripInfoPropertyDto {
    private Long id;
    private Integer numberOfPersons;
    private String destination;
    private Date checkInDate;
    private Date checkOutDate;
    private Long userId;

}
