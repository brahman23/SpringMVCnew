package peaksoft.repository;

import org.springframework.stereotype.Repository;
import peaksoft.model.Hospital;

import java.util.List;
@Repository
public interface HospitalRepository {
    List<Hospital> getAllHospitals();

    void addHospital(Hospital hospital);

    Hospital getHospitalById(Long hospitalId);

    Hospital updateHospital(Long id, Hospital hospital);

    void deleteHospital(Long id);

}
