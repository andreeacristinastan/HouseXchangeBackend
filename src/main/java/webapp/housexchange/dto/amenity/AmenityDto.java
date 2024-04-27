package webapp.housexchange.dto.amenity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import webapp.housexchange.dto.property.PropertyInfoDto;

@AllArgsConstructor
@Getter
@Setter
public class AmenityDto {
    private Long id;
    private String gym;
    private String swimmingPool;
    private String garden;
    private String parking;
    private String wireless;
    private String bikes;
    private String kidsZone;
    private PropertyInfoDto propertyInfo;
}
