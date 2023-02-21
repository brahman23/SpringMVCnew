package peaksoft.service;

import peaksoft.model.Doctor;

import java.util.List;

public interface DoctorService {
    List<Doctor> getAllDoctors(Long id);

    void addDoctors(Doctor doctor,Long id);

    Doctor getDoctorById(Long doctorId);

    void updateDoctor(Long Id, Doctor doctor);

    void deleteDoctor(Long id);
}
