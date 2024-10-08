package backend.entity;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name="image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    @Column(nullable = false)
    private String url;

//    @NonNull
//    @Column(nullable = false)
//    private Boolean profilePicture;

    @ManyToOne
    @JoinColumn(name="property_id", nullable=false)
    @NonNull
    private Property property;

//    @NonNull
//    @ManyToOne
//    @JoinColumn(name="user_id", nullable=false)
//    private User user;
}
