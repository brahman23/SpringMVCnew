package peaksoft.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "hospitals")
@Getter
@Setter
@NoArgsConstructor
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hospital_seq")
    @SequenceGenerator(name = "hospital_seq", sequenceName = "hospital_seq", allocationSize = 2)
    private Long id;

    private String name;

    private String address;
//    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//    private List<Doctor> doctors;

}
