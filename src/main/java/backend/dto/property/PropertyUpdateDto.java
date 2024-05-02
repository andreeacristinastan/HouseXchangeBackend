package backend.dto.property;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PropertyUpdateDto {
    private String name;
    private String country;
    private String city;
    private String address;
    private Integer distance;
    private Boolean allowedPet;
    private String accessibility;
    private Integer numberOfRooms;
}
