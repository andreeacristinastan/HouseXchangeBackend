package webapp.housexchange.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name="amenity")
public class Amenity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    @Column(nullable = false)
    private String gym;

    @NonNull
    @Column(nullable = false)
    private String swimmingPool;

    @NonNull
    @Column(nullable = false)
    private String garden;

    @NonNull
    @Column(nullable = false)
    private String parking;

    @NonNull
    @Column(nullable = false)
    private String wireless;

    @NonNull
    @Column(nullable = false)
    private String bikes;

    @NonNull
    @Column(nullable = false)
    private String kidsZone;

    @NonNull
    @OneToOne
    @JoinColumn(name="property_id", nullable=false)
    private Property property;
}
