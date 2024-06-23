package backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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
    private Date checkInDate;

    @NonNull
    @Column(nullable = false)
    private Date checkOutDate;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    @NonNull
    private User user;

    @ManyToOne
    @JoinColumn(name="property_id", nullable=false)
    @NonNull
    private Property property;
}
