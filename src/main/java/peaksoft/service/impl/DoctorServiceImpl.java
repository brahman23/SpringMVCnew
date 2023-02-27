package peaksoft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.model.Appointment;
import peaksoft.model.Doctor;
import peaksoft.repository.DoctorRepository;
import peaksoft.service.DoctorService;

import java.io.IOException;
import java.util.List;
@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }


    @Override
    public List<Doctor> getAllDoctors(Long id) {
        try {

            return doctorRepository.getAllDoctors(id);


        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Doctor> getAllDoctorsList(Long id) {
        try {

            return doctorRepository.getAllDoctorsList(id);


        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void addDoctors(Doctor doctor, Long id) {
        try {
            doctorRepository.addDoctors(doctor,id);

        }catch (RuntimeException e){
            throw new RuntimeException();
        }

    }

    @Override
    public Doctor getDoctorById(Long doctorId) {
        try {
            return doctorRepository.getDoctorById(doctorId);

        }catch (RuntimeException e){
            throw new RuntimeException();
        }
    }

    @Override
    public void updateDoctor(Long id, Doctor doctor) {
        try {
             doctorRepository.updateDoctor(id, doctor);
        }catch (RuntimeException e){
            throw new RuntimeException();
        }
    }

    @Override
    public void deleteDoctor(Long id) {
        try {
            System.out.println(1);
            Doctor doctor = getDoctorById(id);
            for (Appointment a:doctor.getAppointments()) {
                a.setDoctor(null);
                a.setPatient(null);
            }
            doctorRepository.deleteDoctor(id);
        }catch (RuntimeException e){
            throw new RuntimeException();
        }

    }

    @Override
    public void assignDoctor(Long doctorId, Long appointmentId) throws IOException {
        try {
            doctorRepository.assignDoctor(doctorId, appointmentId);
        }catch (RuntimeException e){
            throw new RuntimeException();
        }

    }
}
