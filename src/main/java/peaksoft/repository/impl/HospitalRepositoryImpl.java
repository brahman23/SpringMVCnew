package peaksoft.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.model.Hospital;
import peaksoft.repository.HospitalRepository;

import java.util.List;
@Repository
@Transactional
public class HospitalRepositoryImpl implements HospitalRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public HospitalRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public List<Hospital> getAllHospitals() {

        try {
            return entityManager.createQuery("select h from Hospital h",Hospital.class).getResultList();

        }catch (RuntimeException e){
            throw new RuntimeException();
        }
    }
    @Override
    public void addHospital(Hospital hospital) {
        try {
            entityManager.persist(hospital);

        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Hospital getHospitalById(Long hospitalId) {
        try {
            Hospital hospital = entityManager.find(Hospital.class,hospitalId);
            return hospital;

        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Hospital updateHospital(Long id,Hospital hospital) {
        try {
                Hospital hospital1 = entityManager.find(Hospital.class,id);
                hospital1.setName(hospital.getName());
                hospital1.setAddress(hospital.getAddress());
                entityManager.merge(hospital1);
                return hospital1;
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
        return null;

    }

    @Override
    public void deleteHospital(Long id) {
        try {
             Hospital hospital = entityManager.find(Hospital.class,id);
            System.out.println("jetii");
                entityManager.remove(hospital);
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }

    }
}
