package backend.dto.meal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MealInfoDto {
    private Long id;
    private Boolean breakfast;
    private Boolean lunch;
    private Boolean dinner;
}
