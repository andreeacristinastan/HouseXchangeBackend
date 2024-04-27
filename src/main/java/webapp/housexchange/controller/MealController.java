package webapp.housexchange.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webapp.housexchange.dto.meal.MealCreationDto;
import webapp.housexchange.dto.meal.MealDto;
import webapp.housexchange.dto.meal.MealUpdateDto;
import webapp.housexchange.service.MealService;

@RestController
//@RequestMapping("/api/meals")
@AllArgsConstructor
public class MealController {
    private MealService mealService;

    //Add meal REST API
    @PostMapping("/api/meals")
    public ResponseEntity<MealDto> createMeal(@RequestBody MealCreationDto mealCreationDto) {
        MealDto savedMeal = mealService.createMeal(mealCreationDto);
        return new ResponseEntity<>(savedMeal, HttpStatus.CREATED);
    }

    //Get meal for a property REST API
    @GetMapping("/api/properties/{property_id}/meals")
    public ResponseEntity<MealDto> getMealById(@PathVariable("property_id") Long propertyId) {
        MealDto mealDto = mealService.getMealById(propertyId);

        return ResponseEntity.ok(mealDto);
    }

    // Update meal REST API
    @PutMapping("/api/properties/{property_id}/meals")
    public ResponseEntity<MealDto> updateMeal(@PathVariable("property_id") Long propertyId,
                                              @RequestBody MealUpdateDto mealUpdateDto) {
        MealDto mealDto = mealService.updateMeal(propertyId, mealUpdateDto);

        return ResponseEntity.ok(mealDto);
    }

    //Delete meal REST API
    @DeleteMapping("/api/properties/{property_id}/meals")
    public ResponseEntity<String> deleteMeal(@PathVariable("property_id") Long propertyId) {
        mealService.deleteMeal(propertyId);

        return ResponseEntity.ok("Meal deleted successfully");
    }
}
