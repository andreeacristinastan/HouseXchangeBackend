package webapp.housexchange.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webapp.housexchange.entity.Meal;

public interface MealRepository extends JpaRepository<Meal, Long> {
}
