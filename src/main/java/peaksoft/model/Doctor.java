package peaksoft.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
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
    @SequenceGenerator(name = "doctor_seq", sequenceName = "doctor_seq", allocationSize = 1)
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
    public void addAppointment(Appointment appointment){
        if (appointments==null){
            appointments=new ArrayList<>();
        }
        appointments.add(appointment);
    }
    @ManyToMany(cascade = {DETACH,MERGE,REFRESH},fetch = FetchType.LAZY,mappedBy = "doctors")
    private List<Department> departments;
    public void addDepartment(Department department){
        if (departments==null){
            departments=new ArrayList<>();
        }
        departments.add(department);
    }

    public Doctor(Long id, String firstName, String lastName, String position, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.email = email;
    }
}
