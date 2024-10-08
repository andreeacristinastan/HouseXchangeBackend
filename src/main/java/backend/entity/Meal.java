package backend.entity;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name="meal")
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    @Column(nullable = false)
    private Boolean breakfast;

    @NonNull
    @Column(nullable = false)
    private Boolean lunch;

    @NonNull
    @Column(nullable = false)
    private Boolean dinner;

    @NonNull
    @OneToOne
    @JoinColumn(name="property_id", nullable=false)
    private Property property;
}
