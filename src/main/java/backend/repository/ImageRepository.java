package backend.repository;

import backend.entity.Image;
import backend.entity.Property;
import backend.entity.Trip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Page<Image> findByProperty(Property property, Pageable pageable);

    List<Image> findByPropertyId(Long propertyId);
}
