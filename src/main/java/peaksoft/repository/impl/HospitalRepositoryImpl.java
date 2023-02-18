package peaksoft.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import peaksoft.model.Hospital;
import peaksoft.repository.HospitalRepository;

import java.util.List;
@Repository
@Transactional
public class HospitalRepositoryImpl implements HospitalRepository {
    @PersistenceContext
    private EntityManager entityManager;



    @Override
    @Transactional
    public List<Hospital> getAllHospitals() {

        try {
            return entityManager.createQuery("select h from Hospital h",Hospital.class).getResultList();

        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    @Transactional
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
            entityManager.find(Hospital.class,hospitalId);

        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void updateHospital(Hospital hospital) {
        try {
                entityManager.merge(hospital);

        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void deleteHospital(Hospital hospital) {
        try {
                entityManager.remove(hospital);
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }

    }
}
