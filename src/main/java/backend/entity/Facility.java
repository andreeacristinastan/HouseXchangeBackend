package backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name="facility")
public class Facility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    @Column(name = "towel", nullable = false)
    private  Boolean towel;

    @NonNull
    @Column(name = "balcony", nullable = false)
    private  Boolean balcony;

    @NonNull
    @Column(name = "air_conditioning", nullable = false)
    private  Boolean airConditioning;

    @NonNull
    @Column(name = "tv", nullable = false)
    private  Boolean tv;

    @NonNull
    @OneToOne
    @JoinColumn(name="property_id", nullable=false)
    private Property property;

//    @NonNull
//    @ManyToOne
//    @JoinColumn(name="room_id", nullable=false)
//    private Room room;


}
