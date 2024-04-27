package webapp.housexchange.entity;

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
    @Column(name = "facilityName", nullable = false, unique = true)
    private  String nameOfFacility;

    @NonNull
    @ManyToOne
    @JoinColumn(name="room_id", nullable=false)
    private Room room;


}
