package webapp.housexchange.dto.property;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import webapp.housexchange.dto.amenity.AmenityInfoDto;
import webapp.housexchange.dto.meal.MealInfoDto;
import webapp.housexchange.dto.room.RoomInfoDto;
import webapp.housexchange.dto.trip.TripInfoPropertyDto;

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
