package webapp.housexchange.dto.amenity;

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
    private String gym;
    private String swimmingPool;
    private String garden;
    private String parking;
    private String wireless;
    private String bikes;
    private String kidsZone;
}
