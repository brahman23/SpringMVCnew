package peaksoft.repository;

import peaksoft.model.Hospital;

import java.util.List;

public interface HospitalRepository {
    List<Hospital> getAllHospitals();

    void addHospital(Hospital hospital);

    Hospital getHospitalById(Long hospitalId);

    void updateHospital(Hospital hospital);

    void deleteHospital(Hospital hospital);

}
