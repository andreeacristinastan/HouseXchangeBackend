package backend.entity;

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
    private Boolean gym;

    @NonNull
    @Column(nullable = false)
    private Boolean swimmingPool;

    @NonNull
    @Column(nullable = false)
    private Boolean garden;

    @NonNull
    @Column(nullable = false)
    private Boolean parking;

    @NonNull
    @Column(nullable = false)
    private Boolean wifi;

    @NonNull
    @Column(nullable = false)
    private Boolean bikes;

    @NonNull
    @Column(nullable = false)
    private Boolean kidsZone;

    @NonNull
    @Column(nullable = false)
    private Boolean petsFriendly;

    @NonNull
    @Column(nullable = false)
    private Boolean disabilitiesFriendly;



    @NonNull
    @OneToOne
    @JoinColumn(name="property_id", nullable=false)
    private Property property;
}
