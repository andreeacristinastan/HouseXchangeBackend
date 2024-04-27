package webapp.housexchange.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import webapp.housexchange.entity.Property;
import webapp.housexchange.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Page<Room> findByProperty(Property property, Pageable pageable);
}
