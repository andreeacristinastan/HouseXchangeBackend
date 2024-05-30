package backend.dto.amenity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AmenityInfoDto {
    private Long id;
    private Boolean gym;
    private Boolean swimmingPool;
    private Boolean garden;
    private Boolean parking;
    private Boolean wifi;
    private Boolean bikes;
    private Boolean kidsZone;
    private Boolean petsFriendly;
    private Boolean disabilitiesFriendly;
}
