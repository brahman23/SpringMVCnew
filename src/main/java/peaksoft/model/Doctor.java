package peaksoft.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.CascadeType.*;


@Entity
@Table(name = "doctors")
@Getter
@Setter
@NoArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doctor_seq")
    @SequenceGenerator(name = "doctor_seq", sequenceName = "doctor_seq", allocationSize = 2)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column
    private String position;
    @Column
    private String email;

    @ManyToOne(cascade = {DETACH,MERGE,REFRESH},fetch = FetchType.EAGER)
    private Hospital hospital;
    @OneToMany(cascade = {DETACH,MERGE,REFRESH},fetch = FetchType.LAZY,mappedBy = "doctor")
    private List<Appointment> appointments;
    @ManyToMany(cascade = {DETACH,MERGE,REFRESH},fetch = FetchType.LAZY,mappedBy = "doctors")
    private List<Department> departments;

    public Doctor(Long id, String firstName, String lastName, String position, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.email = email;
    }
}
