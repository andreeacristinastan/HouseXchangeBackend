package backend.repository;

import backend.entity.Availability;
import backend.entity.Facility;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvailabilityRepository extends JpaRepository<Availability, Long> {
}
