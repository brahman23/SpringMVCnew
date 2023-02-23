package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Appointment;
import peaksoft.model.Patient;
import peaksoft.service.AppointmentService;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;
    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }
    @GetMapping("/{id}")
    public String getAllAppointment(@PathVariable Long id, Model model){
        model.addAttribute("appointments",appointmentService.getAllAppointments(id));
        model.addAttribute("hospital",id);
        return "/appointment/appointments";
    }

    @GetMapping("/{id}/addAppointment")
    public String addAppointment(Model model,@PathVariable Long id){
        model.addAttribute("appointment",new Appointment());
        model.addAttribute("hospital",id);
        return "/appointment/addAppointment" ;
    }
    @PostMapping("/{id}/saveAppointment")
    public String savePatient(@ModelAttribute("appointment")  Appointment appointment,
                              @PathVariable Long id) {
        appointmentService.addAppointment(appointment, id);
        return "redirect:/appointments/"+id;
    }
    /*
    @GetMapping("/updateGroup/{courseId}/{id}")
    public String updateGroup(@PathVariable("id") Long id, @PathVariable("courseId") Long courseId, Model model) {
        Group group = groupService.getGroupById(id);
        model.addAttribute("group", group);

        model.addAttribute("courseId", courseId);
        model.addAttribute("id",id);
        return "/group/update_group";
    }
    @PostMapping("/{courseId}/{id}/saveUpdateGroup")
    public String saveUpdateCourse(@PathVariable("courseId") Long courseId,
                                   @PathVariable("id") Long id,
                                   @ModelAttribute("group") Group group) {
        groupService.updateGroup(group,id);
        return "redirect:/groups/"+courseId;
    }
     */
    @GetMapping("/updateAppointment/{hospitalId}/{id}")
    public String updateAppointment(@PathVariable("id") Long id,@PathVariable("hospitalId") Long hospitalId, Model model) {
        Appointment  appointment = appointmentService.getAppointmentById(id);
        System.out.println("11");
        model.addAttribute("appointment",appointment);
        model.addAttribute("hospitalId", hospitalId);
        model.addAttribute("id",id);
        return "/appointment/updateAppointment";
    }

    @PostMapping("/{hospitalId}/{id}/saveUpdateAppointment")
    public String saveUpdateAppointment(@PathVariable("hospitalId") Long hospitalId,
                                    @PathVariable("id") Long id,
                                    @ModelAttribute("appointment") Appointment appointment) {
        System.out.println("22");
        appointmentService.updateAppointment(id,appointment);
        return "redirect:/appointments/"+hospitalId;
    }

    @GetMapping ("/{hospitalId}/{id}/deleteAppointment")
    public String deleteAppointment(@PathVariable("id") Long id, @PathVariable("hospitalId") Long hospitalId) {
        appointmentService.deleteAppointment(id,hospitalId);
        return "redirect:/appointments/"+hospitalId;
    }
}
