package backend.mapper;

import backend.dto.meal.MealCreationDto;
import backend.dto.meal.MealDto;
import backend.dto.meal.MealInfoDto;
import backend.entity.Meal;
import backend.entity.Property;

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
