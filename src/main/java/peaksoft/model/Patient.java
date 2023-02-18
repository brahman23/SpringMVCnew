package peaksoft.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.model.gender.Gender;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;


@Entity
@Table(name = "patients")
@Getter
@Setter
@NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patient_seq")
    @SequenceGenerator(name = "patient_seq", sequenceName = "patient_seq", allocationSize = 2)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone_number")
    private String phoneNumber;
    private Gender gender;
    private String email;
    @ManyToOne(cascade = {DETACH,MERGE,REFRESH},fetch = FetchType.EAGER)
    private Hospital hospital;
//    public void addCourse(Hospital hospital){
//        if (hospital==null){
//            hospital=new ArrayList<>();
//        }
//        hospital.add(hospital);
//    }
    @OneToMany(cascade = {DETACH,MERGE,REFRESH,REMOVE},fetch = FetchType.LAZY,mappedBy = "patient")
    private List<Appointment> appointments;
    public void addCourse(Appointment appointment){
        if (appointments==null){
            appointments = new ArrayList<>();
        }
        appointments.add(appointment);
    }

    public Patient(Long id, String firstName, String lastName, String phoneNumber, Gender gender, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.email = email;
    }
}
