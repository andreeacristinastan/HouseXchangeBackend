package backend.dto.room;

import lombok.AllArgsConstructor;
import lombok.Getter;
import backend.dto.facility.FacilityDto;

import java.util.List;

@AllArgsConstructor
@Getter
public class RoomDto {
    private long id;
    private String accessibility;
    private Long propertyId;
    private List<FacilityDto> facilities;

}
