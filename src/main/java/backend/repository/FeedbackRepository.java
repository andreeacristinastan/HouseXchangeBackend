package backend.repository;

import backend.entity.Feedback;
import backend.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}
