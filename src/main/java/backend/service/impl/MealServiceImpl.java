package backend.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import backend.dto.meal.MealCreationDto;
import backend.dto.meal.MealDto;
import backend.dto.meal.MealUpdateDto;
import backend.entity.Meal;
import backend.entity.Property;
import backend.entity.User;
import backend.exceptions.BadRequestException;
import backend.exceptions.DatabaseException;
import backend.exceptions.ResourceNotFoundException;
import backend.mapper.MealMapper;
import backend.repository.MealRepository;
import backend.repository.PropertyRepository;
import backend.repository.UserRepository;
import backend.security.AuthUtil;
import backend.service.MealService;
import backend.utils.CheckPermissionsHelper;

@Service
@AllArgsConstructor
public class MealServiceImpl implements MealService {
    private PropertyRepository propertyRepository;
    private MealRepository mealRepository;
    private UserRepository userRepository;
    private AuthUtil authUtil;
    private CheckPermissionsHelper checkPermissionsHelper;

    @Override
    public MealDto createMeal(MealCreationDto mealCreationDto) {
        Property property;
        try {
            property = propertyRepository.findById(mealCreationDto.getPropertyId()).orElseThrow(
                    () -> new ResourceNotFoundException("Property not found"));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

        User user;
        try {
            user = userRepository.findById(property.getUser().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

        checkPermissionsHelper.checkAuth(user.getEmail(), authUtil);

        Meal meal = MealMapper.mapToMeal(mealCreationDto, property);
        meal.setProperty(property);
        Meal savedMeal;

        try {
            savedMeal = mealRepository.save(meal);
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }
        return MealMapper.mapToMealDto(savedMeal);

    }

    @Override
    public MealDto getMealById(Long propertyId) {
        Property property;

        try {
            property = propertyRepository.findById(propertyId).orElseThrow(
                    () -> new ResourceNotFoundException("Property not found"));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

        if(property.getMeal() != null) {
            return MealMapper.mapToMealDto(property.getMeal());
        } else {
            throw new ResourceNotFoundException("Meal not found");
        }
    }

    @Override
    public MealDto updateMeal(Long propertyId, MealUpdateDto mealUpdateDto) {
        Property property;
        try {
        property = propertyRepository.findById(propertyId).orElseThrow(
                () -> new ResourceNotFoundException("Property not found"));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

        User user;
        try {
            user = userRepository.findById(property.getUser().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

        checkPermissionsHelper.checkAuth(user.getEmail(), authUtil);

        if(property.getMeal() != null) {
            Meal meal = property.getMeal();

            meal.setBreakfast(mealUpdateDto.getBreakfast());
            meal.setLunch(mealUpdateDto.getLunch());
            meal.setDinner(mealUpdateDto.getDinner());

            Meal updatedObj;

            try {
                updatedObj = mealRepository.save(meal);
            } catch (DataAccessException exception) {
                throw new DatabaseException("Exception occurred while accessing the database", exception);
            }
            return MealMapper.mapToMealDto(updatedObj);
        } else {
            throw new ResourceNotFoundException("Meal not found for the given property");
        }

    }

    @Override
    public void deleteMeal(Long propertyId) {
        Property property;
        try {
            property = propertyRepository.findById(propertyId).orElseThrow(
                    () -> new ResourceNotFoundException("Property not found"));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

        User user;
        try {
            user = userRepository.findById(property.getUser().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

        checkPermissionsHelper.checkAuth(user.getEmail(), authUtil);

        if(property.getMeal() != null) {
            property.setMeal(null);
            try {
                propertyRepository.save(property);
            } catch (DataAccessException exception) {
                throw new DatabaseException("Exception occurred while accessing the database", exception);
            }
        } else {
            throw new BadRequestException("Cannot delete a meal that doesn't exists");
        }
    }
}
