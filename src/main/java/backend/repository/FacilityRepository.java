package backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import backend.entity.Facility;
//import backend.entity.Room;

public interface FacilityRepository extends JpaRepository<Facility, Long> {
//    Page<Facility> findByRoom(Room room, Pageable pageable);

}
