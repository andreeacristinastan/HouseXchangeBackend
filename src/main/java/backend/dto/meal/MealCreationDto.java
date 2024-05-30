package backend.dto.meal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class MealCreationDto {
    private Boolean breakfast;
    private Boolean lunch;
    private Boolean dinner;
}
