package peaksoft.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.model.Doctor;
import peaksoft.model.Hospital;
import peaksoft.repository.DoctorRepository;

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
    public void addDoctors(Doctor doctor,Long id) {
        Hospital hospital =entityManager.find(Hospital.class,id);
        hospital.addDoctor(doctor);
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
        doctor.setHospital(null);
        entityManager.remove(doctor);

    }
}
