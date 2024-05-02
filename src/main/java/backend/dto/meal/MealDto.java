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
    private String breakfast;
    private String lunch;
    private String dinner;
    private PropertyInfoDto propertyInfo;
}
