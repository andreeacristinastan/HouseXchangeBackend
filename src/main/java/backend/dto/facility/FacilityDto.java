package backend.dto.facility;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FacilityDto {
    private long id;
    private  String nameOfFacility;
    private Long roomId;
}
