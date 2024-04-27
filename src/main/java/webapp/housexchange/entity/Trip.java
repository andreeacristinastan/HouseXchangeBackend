package webapp.housexchange.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name="trip")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    @Column(nullable = false)
    private Integer numberOfPersons;

    @NonNull
    @Column(nullable = false)
    private String destination;

    @NonNull
    @Column(nullable = false)
    private Integer minRange;

    @NonNull
    @Column(nullable = false)
    private Integer maxRange;

    @NonNull
    @Column(nullable = false)
    private LocalDate checkInDate;

    @NonNull
    @Column(nullable = false)
    private LocalDate checkOutDate;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    @NonNull
    private User user;

//    @NonNull
//    @OneToOne
//    @JoinColumn(name="property_id", nullable=false)
//    private Property property;

    @ManyToOne
    @JoinColumn(name="property_id", nullable=false)
    @NonNull
    private Property property;
}
