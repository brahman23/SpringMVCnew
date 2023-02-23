package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Department;
import peaksoft.model.Patient;
import peaksoft.service.PatientService;

@Controller
@RequestMapping("/patients")
public class PatientController {

private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/{id}")
    public String getAllPatients(@PathVariable Long id, Model model){
        model.addAttribute("patients",patientService.getAllPatient(id));
        model.addAttribute("hospital",id);
        return "/patient/patients";
    }

    @GetMapping("/{id}/addPatient")
    public String addPatient(Model model,@PathVariable Long id){
        model.addAttribute("patient",new Patient());
        model.addAttribute("hospital",id);
        return "/patient/addPatient" ;
    }
    @PostMapping("/{id}/savePatient")
    public String savePatient(@ModelAttribute("patient")  Patient patient,
                                 @PathVariable Long id) {
        patientService.addPatient(patient,id);
        return "redirect:/patients/"+id;
    }

    @GetMapping("/updatePatient/{id}")
    public String updatePatient(@PathVariable("id") Long id, Model model) {
        Patient patient = patientService.getPatientById(id);
        model.addAttribute("patient",patient);
        model.addAttribute("hospitalId", patient.getHospital().getId());
        return "/patient/updatePatient";
    }

    @PostMapping("/{hospitalId}/{id}/saveUpdatePatient")
    public String saveUpdatePatient(@PathVariable("hospitalId") Long hospitalId,
                                       @PathVariable("id") Long id,
                                       @ModelAttribute("patient") Patient patient) {
        patientService.updatePatient(id,patient);
        return "redirect:/patients/"+hospitalId;
    }

    @GetMapping ("/{hospitalId}/{id}/deletePatient")
    public String deletePatient(@PathVariable("id") Long id, @PathVariable("hospitalId") Long hospitalId) {
        patientService.deletePatient(id);
        return "redirect:/patients/"+hospitalId;
    }
    
}
