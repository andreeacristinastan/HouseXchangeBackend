package backend.dto.property;

import backend.dto.amenity.AmenityCreationDto;
import backend.dto.facility.FacilityCreationDto;
import backend.dto.meal.MealCreationDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PropertyUpdateDto {
    private String name;
    private String propertyDescription;
    private Long numberOfBathrooms;
    private Long numberOfRooms;
    private Long price;
    private FacilityCreationDto facilities;
    private AmenityCreationDto amenities;

}
