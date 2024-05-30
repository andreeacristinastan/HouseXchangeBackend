package backend.dto.amenity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import backend.dto.property.PropertyInfoDto;

@AllArgsConstructor
@Getter
@Setter
public class AmenityDto {
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
    private PropertyInfoDto propertyInfo;
}
