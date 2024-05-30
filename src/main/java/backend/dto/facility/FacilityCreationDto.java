package backend.dto.facility;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class FacilityCreationDto {
    private  Boolean towel;
    private  Boolean balcony;
    private  Boolean airConditioning;
    private  Boolean tv;
//    private Long propertyId;


}
