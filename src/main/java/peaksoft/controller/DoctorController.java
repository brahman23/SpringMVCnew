package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Doctor;
import peaksoft.service.DoctorService;

@Controller
@RequestMapping("")
public class DoctorController {
    private final DoctorService doctorService;
    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }
    @GetMapping("/doctors/{id}")
    public String getAllDoctors(@PathVariable Long id, Model model){
        model.addAttribute("doctors",doctorService.getAllDoctors(id));
        model.addAttribute("hospital",id);

        return "/doctor/doctors";
    }

    @GetMapping("doctors/{id}/addDoctor")
    public String addDoctor(Model model,@PathVariable Long id){
        model.addAttribute("doctor",new Doctor());
        model.addAttribute("hospital",id);


        return "/doctor/addDoctor";
    }
    @PostMapping("/{id}/saveDoctor")
    public String saveDoctor(@ModelAttribute("doctor") Doctor doctor, BindingResult bindingResult,
                             @PathVariable Long id) {
        if (bindingResult.hasErrors()){
            return "redirect:/doctors/"+id+"/addDoctor";
        }
        doctorService.addDoctors(doctor,id);
        return "redirect:/doctors/"+id;
    }

    @GetMapping("/updateDoctor/{id}")
    public String updateDoctor(@PathVariable("id") Long id, Model model) {
        Doctor doctor = doctorService.getDoctorById(id);
        model.addAttribute("doctor", doctor);
        model.addAttribute("hospitalId", doctor.getHospital().getId());
        return "/doctor/updateDoctors";
    }

    @PostMapping("/{hospitalId}/{id}/saveUpdateDoctor")
    public String saveUpdateDoctor(@PathVariable("hospitalId") Long hospitalId,
                                   @PathVariable("id") Long id,
                                   @ModelAttribute("doctor") Doctor doctor) {
        doctorService.updateDoctor(id,doctor);
        return "redirect:/doctors/"+hospitalId;
    }

    @DeleteMapping("/{hospitalId}/{id}/deleteDoctor")
    public String deleteDoctor(@PathVariable("id") Long id, @PathVariable("hospitalId") Long hospitalId) {
        doctorService.deleteDoctor(id);
        return "redirect:/doctors/"+hospitalId;
    }
}
