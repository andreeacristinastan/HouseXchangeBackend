package backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name="property")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    @Column(nullable = false)
    private String name;

    @NonNull
    @Column(nullable = false)
    private String country;

    @NonNull
    @Column(nullable = false)
    private String city;

    @NonNull
    @Column(nullable = false)
    private String address;

    @NonNull
    @Column(nullable = false)
    private Integer distance;

    @NonNull
    @Column(nullable = false)
    private Boolean allowedPet;

    @NonNull
    @Column(nullable = false)
    private String accessibility;

    @Column(nullable = false)
    private Integer numberOfRooms = 0;

    @Column(nullable = false)
    private Integer price = 0;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    @NonNull
    private User user;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    @NonNull
    private List<Room> rooms;

//    @OneToOne(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Trip trip;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Trip> trips;

    @OneToOne(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    private Meal meal;

    @OneToOne(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    private Amenity amenity;



}
