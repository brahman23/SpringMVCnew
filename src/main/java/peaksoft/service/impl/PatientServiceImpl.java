package peaksoft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.model.Patient;
import peaksoft.repository.PatientRepository;
import peaksoft.service.PatientService;

import java.io.IOException;
import java.util.List;
@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public List<Patient> getAllPatient(Long id) {
        try {
            return patientRepository.getAllPatient(id);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Patient> getAllPatientList(Long id) {
        try {
            return patientRepository.getAllPatientList(id);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void addPatient(Patient patient, Long id) {
        try {
            patientRepository.addPatient(patient, id);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public Patient getPatientById(Long Id) {
        try {
            return patientRepository.getPatientById(Id);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void updatePatient(Long Id, Patient patient) {
        try {
            patientRepository.updatePatient(Id, patient);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void deletePatient(Long id) {
        try {
            patientRepository.deletePatient(id);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void assignPatient(Long patientId, Long appointmentId) throws IOException {
        try {
            patientRepository.assignPatient(patientId, appointmentId);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }


    }
}
