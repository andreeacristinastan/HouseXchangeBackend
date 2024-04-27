package webapp.housexchange.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webapp.housexchange.entity.Property;

public interface PropertyRepository extends JpaRepository<Property, Long> {
    boolean existsByNameAndCountryAndCityAndAddress(String name, String country, String city, String address);
}
