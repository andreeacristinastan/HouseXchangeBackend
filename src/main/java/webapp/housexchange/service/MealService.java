package webapp.housexchange.service;

import webapp.housexchange.dto.meal.MealCreationDto;
import webapp.housexchange.dto.meal.MealDto;
import webapp.housexchange.dto.meal.MealUpdateDto;

public interface MealService {
    public MealDto createMeal(MealCreationDto mealCreationDto);

    MealDto getMealById(Long propertyId);

    MealDto updateMeal(Long propertyId, MealUpdateDto mealUpdateDto);

    void deleteMeal(Long propertyId);
}
