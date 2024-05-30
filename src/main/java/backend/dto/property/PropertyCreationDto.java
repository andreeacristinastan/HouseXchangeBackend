package backend.dto.property;

import backend.dto.amenity.AmenityCreationDto;
import backend.dto.facility.FacilityCreationDto;
import backend.dto.facility.FacilityDto;
import backend.dto.meal.MealCreationDto;
import backend.entity.Facility;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class PropertyCreationDto {
    private String name;
    private String country;
    private String city;
    private String address;
    private Long zipCode;
    private String propertyDescription;
    private String propertyType;
    private Long numberOfBathrooms;
    private Long numberOfRooms;
    private Long price;
    private Long userId;
    private FacilityCreationDto facilities;
    private AmenityCreationDto amenities;
    private MealCreationDto meals;
}
