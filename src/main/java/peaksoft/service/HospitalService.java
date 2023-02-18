package peaksoft.service;

import peaksoft.model.Hospital;

import java.util.List;

public interface HospitalService {
    List<Hospital> getAllHospitals();

    void addHospital(Hospital hospital);

    Hospital getHospitalById(Long hospitalId);

    void updateHospital(Hospital hospital);

    void deleteHospital(Hospital hospital);
}
