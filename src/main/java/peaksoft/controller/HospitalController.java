package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Hospital;
import peaksoft.service.HospitalService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HospitalController {

    private HospitalService hospitalS;
    @Autowired
    public HospitalController(HospitalService hospitalService) {
        this.hospitalS = hospitalService;
    }
    @GetMapping("/getAll")
    public String getAll(Model model){
        List<Hospital> hospitals = hospitalS.getAllHospitals();
        model.addAttribute("hospitals",hospitals);
        return "/hospital/getAllHospital";
    }
    @GetMapping("addHospital")
    public String addHospital(Model model){
        model.addAttribute("hospital",new Hospital());
        return "/hospital/addHospital";
    }
    @PostMapping("saveHospital")
    public String saveCompany(@ModelAttribute("hospital")Hospital hospital, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "/hospital/addHospital";
        }

        hospitalS.addHospital(hospital);
        return "redirect:/getAll";
    }
    @GetMapping("updateHospital")
    public String updateHospital(@RequestParam("hospitalId") Long id, Model model) {
        Hospital hospital = hospitalS.getHospitalById(id);
        model.addAttribute("hospital", hospital);
        return "/hospital/updateHospital";
    }

    @PutMapping("/updateHospital")
    public String saveUpdateHospital(@ModelAttribute("hospital")Hospital hospital, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "/hospital/updateHospital";
        }
        hospitalS.updateHospital(hospital);
        return "redirect:/getAll";
    }
    @DeleteMapping("/deleteHospital")
    public String deleteCompany(@RequestParam("hospitalId") Long id) {
        hospitalS.deleteHospital(hospitalS.getHospitalById(id));
        return "redirect:/getAll";
    }

}
