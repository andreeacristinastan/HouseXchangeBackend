package webapp.housexchange.dto.meal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class MealUpdateDto {
    private String breakfast;
    private String lunch;
    private String dinner;
}
