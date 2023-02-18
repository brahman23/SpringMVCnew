package peaksoft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.model.Hospital;
import peaksoft.repository.HospitalRepository;

import java.util.List;
@Service

public class HospitalService implements peaksoft.service.HospitalService {

    private HospitalRepository hospitalRepository;
    @Autowired
    public HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    @Override
    public List<Hospital> getAllHospitals() {
        try {
            hospitalRepository.getAllHospitals();

        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void addHospital(Hospital hospital) {
        try {
            hospitalRepository.addHospital(hospital);
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public Hospital getHospitalById(Long hospitalId) {
        try {
                hospitalRepository.getHospitalById(hospitalId);
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void updateHospital(Hospital hospital) {
        try {
                hospitalRepository.updateHospital(hospital);
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void deleteHospital(Hospital hospital) {
        try {
                hospitalRepository.deleteHospital(hospital);
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }

    }
}
