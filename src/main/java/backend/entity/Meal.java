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
    private String breakfast;

    @NonNull
    @Column(nullable = false)
    private String lunch;

    @NonNull
    @Column(nullable = false)
    private String dinner;

    @NonNull
    @OneToOne
    @JoinColumn(name="property_id", nullable=false)
    private Property property;
}
