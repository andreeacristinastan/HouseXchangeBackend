package backend.dto.meal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import backend.dto.property.PropertyInfoDto;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MealDto {
    private Long id;
    private Boolean breakfast;
    private Boolean lunch;
    private Boolean dinner;
    private PropertyInfoDto propertyInfo;
}
