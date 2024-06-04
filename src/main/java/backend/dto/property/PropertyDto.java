package backend.dto.property;

import backend.dto.facility.FacilityDto;
import backend.dto.image.ImageDto;
import backend.dto.image.ImageInfoDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import backend.dto.amenity.AmenityInfoDto;
import backend.dto.meal.MealInfoDto;
import backend.dto.room.RoomInfoDto;
import backend.dto.trip.TripInfoPropertyDto;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PropertyDto {
    private long id;
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
    private List<TripInfoPropertyDto> trips;
    private List<ImageInfoDto> images;
    private MealInfoDto mealInfo;
    private AmenityInfoDto amenityInfo;
    private FacilityDto facilityDto;
}
