package backend.dto.property;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PropertyUpdateDto {
    private String name;
//    private String country;
//    private String city;
//    private String address;
//    private Long zipCode;
    private String propertyDescription;
//    private String propertyType;
    private Long numberOfBathrooms;
    private Long numberOfRooms;
    private Long price;

}
