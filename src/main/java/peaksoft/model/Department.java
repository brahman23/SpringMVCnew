package peaksoft.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.CascadeType.*;


@Entity
@Table(name = "departments")
@Getter
@Setter
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "department_seq")
    @SequenceGenerator(name = "department_seq", sequenceName = "department_seq", allocationSize = 2)
    private Long id;
    private String name;
    @ManyToOne(cascade = {DETACH,MERGE,REFRESH},fetch = FetchType.EAGER)
    private Hospital hospital;
    @ManyToMany(cascade = {DETACH,MERGE,REFRESH},fetch = FetchType.LAZY)
    private List<Doctor> doctors;

    public Department(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Department(Long id, String name, Hospital hospital, List<Doctor> doctors) {
        this.id = id;
        this.name = name;
        this.hospital = hospital;
        this.doctors = doctors;
    }
}
