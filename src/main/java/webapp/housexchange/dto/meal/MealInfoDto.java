package webapp.housexchange.dto.meal;

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
    private String breakfast;
    private String lunch;
    private String dinner;
}
