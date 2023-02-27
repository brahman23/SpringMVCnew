package peaksoft.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Id;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.model.*;
import peaksoft.repository.PatientRepository;

import java.io.IOException;
import java.util.List;
@Repository
@Transactional
public class PatientRepositoryImpl implements PatientRepository {
    @PersistenceContext
    private final EntityManager entityManager;
    @Autowired
    public PatientRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Patient> getAllPatient(Long id) {
        return entityManager.createQuery("select d from Patient d where d.hospital.id = :id", Patient.class).setParameter("id", id).getResultList();
    }

    @Override
    public List<Patient> getAllPatientList(Long id) {
        return entityManager.createQuery("select d from Patient d where d.hospital.id = :id", Patient.class).setParameter("id", id).getResultList();
    }

    @Override
    public void addPatient(Patient patient, Long id) {
        Hospital hospital =entityManager.find(Hospital.class,id);
        hospital.addPatient(patient);
        hospital.addCount();
        patient.setHospital(hospital);
        entityManager.merge(patient);

    }

    @Override
    public Patient getPatientById(Long Id) {
        return entityManager.find(Patient.class,Id);
    }

    @Override
    public void updatePatient(Long Id, Patient patient) {
        Patient patient1 = entityManager.find(Patient.class,Id);
        patient1.setFirstName(patient.getFirstName());
        patient1.setLastName(patient.getLastName());
        patient1.setEmail(patient.getEmail());
        patient1.setPhoneNumber(patient.getPhoneNumber());
        patient1.setGender(patient.getGender());


        entityManager.merge(patient1);

    }

    @Override
    public void deletePatient(Long id) {
       Patient patient = entityManager.find(Patient.class, id);

       patient.getHospital().deleteCount();

        entityManager.remove(patient);
    }

    @Override
    public void assignPatient(Long patientId, Long appointmentId) throws IOException {
        System.out.println("patient");

        Appointment appointment = entityManager.find(Appointment.class, appointmentId);
        Patient patient = entityManager.find(Patient.class, patientId);
        if (patient.getAppointments() != null) {
            for (Appointment a : patient.getAppointments()) {
                if (a.getId() == appointmentId) {
                    throw new IOException("myndai  bar!!!");
                }
            }
        }
        appointment.setPatient(patient);
        patient.addAppointment(appointment);
        entityManager.merge(appointment);
        entityManager.merge(patient);

    }
}
