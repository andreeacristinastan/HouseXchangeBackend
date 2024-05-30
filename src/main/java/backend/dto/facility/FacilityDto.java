package backend.dto.facility;

import backend.dto.property.PropertyInfoDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FacilityDto {
    private long id;
    private  Boolean towel;
    private  Boolean balcony;
    private  Boolean airConditioning;
    private  Boolean tv;
    private PropertyInfoDto propertyInfo;

}
