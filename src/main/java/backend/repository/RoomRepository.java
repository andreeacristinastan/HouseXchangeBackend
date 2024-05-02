package backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import backend.entity.Property;
import backend.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Page<Room> findByProperty(Property property, Pageable pageable);
}
