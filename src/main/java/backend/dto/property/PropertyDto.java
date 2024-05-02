package backend.dto.property;

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
    private Integer distance;
    private Boolean allowedPet;
    private String accessibility;
    private Integer numberOfRooms;
    private Long userId;
    private List<RoomInfoDto> rooms;
    private List<TripInfoPropertyDto> trips;
    private MealInfoDto mealInfo;
    private AmenityInfoDto amenityInfo;
}
