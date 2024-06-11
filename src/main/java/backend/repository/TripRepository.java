package backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import backend.entity.Trip;
import backend.entity.User;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findByUser(User user);
    @RestResource(path = "findByUserPageable")
    Page<Trip> findByUser(User user, Pageable pageable);
}
