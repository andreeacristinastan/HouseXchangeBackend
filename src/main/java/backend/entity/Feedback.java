package backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name="feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    @Column(nullable = false)
    private String feedback;

    @NonNull
    @Column(nullable = false)
    private Boolean toTheProperty;

    @NonNull
    @Column(nullable = false)
    private Boolean toTheApp;

    @NonNull
    @Column(nullable = false)
    private Boolean other;

    @NonNull
    @Column(nullable = false)
    private Long userId;

}
