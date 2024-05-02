package backend.dto.amenity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AmenityCreationDto {
    private String gym;
    private String swimmingPool;
    private String garden;
    private String parking;
    private String wireless;
    private String bikes;
    private String kidsZone;
    private Long propertyId;

}
