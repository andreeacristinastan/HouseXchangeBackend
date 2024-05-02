package backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import backend.entity.Meal;

public interface MealRepository extends JpaRepository<Meal, Long> {
}
