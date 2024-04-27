package webapp.housexchange.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import webapp.housexchange.entity.Trip;
import webapp.housexchange.entity.User;

public interface TripRepository extends JpaRepository<Trip, Long> {
    Page<Trip> findByUser(User user, Pageable pageable);
}
