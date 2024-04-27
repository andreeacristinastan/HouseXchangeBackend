package webapp.housexchange.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webapp.housexchange.entity.Amenity;

public interface AmenityRepository extends JpaRepository<Amenity, Long> {
}
