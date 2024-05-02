package backend.dto.property;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PropertyCreationDto {
    private String name;
    private String country;
    private String city;
    private String address;
    private Integer distance;
    private Boolean allowedPet;
    private String accessibility;
    private Long userId;
}
