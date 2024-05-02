package backend.service;

import backend.dto.meal.MealCreationDto;
import backend.dto.meal.MealDto;
import backend.dto.meal.MealUpdateDto;

public interface MealService {
    public MealDto createMeal(MealCreationDto mealCreationDto);

    MealDto getMealById(Long propertyId);

    MealDto updateMeal(Long propertyId, MealUpdateDto mealUpdateDto);

    void deleteMeal(Long propertyId);
}
