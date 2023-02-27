package peaksoft.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.model.Appointment;
import peaksoft.model.Department;
import peaksoft.model.Doctor;
import peaksoft.model.Hospital;
import peaksoft.repository.DoctorRepository;

import java.io.IOException;
import java.util.List;
@Repository
@Transactional
public class DoctorRepositoryImpl implements DoctorRepository {
    @PersistenceContext
    private final EntityManager entityManager;


    @Autowired
    public DoctorRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Doctor> getAllDoctors(Long id) {
        return entityManager.createQuery("select d from Doctor d where d.hospital.id = :id",Doctor.class).setParameter("id", id).getResultList();
    }
    @Override
    public List<Doctor> getAllDoctorsList(Long id) {
        return entityManager.createQuery("select d from Doctor d where d.hospital.id = :id",Doctor.class).setParameter("id", id).getResultList();
    }

    @Override
    public void addDoctors(Doctor doctor,Long id) {
        Hospital hospital =entityManager.find(Hospital.class,id);
        hospital.addDoctor(doctor);
        hospital.addCountD();
        doctor.setHospital(hospital);

        entityManager.merge(doctor);


    }

    @Override
    public Doctor getDoctorById(Long doctorId) {
        return entityManager.find(Doctor.class,doctorId);
    }

    @Override
    public void updateDoctor(Long id, Doctor doctor) {
        Doctor doctor1 = getDoctorById(id);
        doctor1.setFirstName(doctor.getFirstName());
        doctor1.setLastName(doctor.getLastName());
        doctor1.setPosition(doctor.getPosition());
        doctor1.setEmail(doctor.getEmail());
        entityManager.merge(doctor1);

    }

    @Override
    public void deleteDoctor(Long id) {
        Doctor doctor = entityManager.find(Doctor.class, id);
        doctor.getHospital().deleteCountD();
        for (int i = 0; i < doctor.getAppointments().size(); i++) {
            doctor.getAppointments().get(i).setDoctor(null);
        }
//        doctor.setHospital(null);
        entityManager.remove(doctor);

    }


    @Override
    public void assignDoctor(Long doctorId, Long appointmentId) throws IOException {
        System.out.println("doctor");

        Appointment appointment = entityManager.find(Appointment.class, appointmentId);
        Doctor doctor = entityManager.find(Doctor.class, doctorId);
        if (doctor.getAppointments() != null) {
            for (Appointment a : doctor.getAppointments()) {
                if (a.getId() == appointmentId) {
                    throw new IOException("myndai  bar!!!");
                }
            }
        }
        appointment.setDoctor(doctor);
        doctor.addAppointment(appointment);
        entityManager.merge(appointment);
        entityManager.merge(doctor);

    }
}
