package webapp.housexchange.mapper;

import webapp.housexchange.dto.meal.MealCreationDto;
import webapp.housexchange.dto.meal.MealDto;
import webapp.housexchange.dto.meal.MealInfoDto;
import webapp.housexchange.entity.Meal;
import webapp.housexchange.entity.Property;

public class MealMapper {

    public static MealInfoDto mapToMealInfoDto(Meal meal) {
        return new MealInfoDto(
                meal.getId(),
                meal.getBreakfast(),
                meal.getLunch(),
                meal.getDinner()
        );
    }

    public static Meal mapToMeal(MealCreationDto mealCreationDto, Property property) {
        return new Meal(
                mealCreationDto.getBreakfast(),
                mealCreationDto.getLunch(),
                mealCreationDto.getDinner(),
                property
        );
    }

    public static MealDto mapToMealDto(Meal meal) {
        return new MealDto(
                meal.getId(),
                meal.getBreakfast(),
                meal.getLunch(),
                meal.getDinner(),
                PropertyMapper.mapToPropertyInfoDto(meal.getProperty())
        );
    }
}
