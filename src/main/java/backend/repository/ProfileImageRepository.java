package backend.repository;

import backend.entity.ProfileImage;
import backend.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileImageRepository extends JpaRepository<ProfileImage, Long> {
}
