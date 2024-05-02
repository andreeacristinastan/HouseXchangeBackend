package backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import backend.entity.Amenity;

public interface AmenityRepository extends JpaRepository<Amenity, Long> {
}
