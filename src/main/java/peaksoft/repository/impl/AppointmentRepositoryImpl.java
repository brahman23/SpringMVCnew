package peaksoft.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.model.Appointment;
import peaksoft.model.Hospital;
import peaksoft.repository.AppointmentRepository;

import java.util.List;
@Repository
@Transactional
public class AppointmentRepositoryImpl implements AppointmentRepository {
    @PersistenceContext
    private final EntityManager manager;
    @Autowired
    public AppointmentRepositoryImpl(EntityManager manager) {
        this.manager = manager;
    }


    @Override
    public List<Appointment> getAllAppointments(Long id) {
        try {
            List<Appointment> list = manager.find(Hospital.class,id).getAppointments();
            list.forEach(System.out::println);

            return list;

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("exeption all");
        return null;


    }

    @Override
    public void addAppointment(Appointment appointment, Long id) {
        try {
            Hospital hospital = manager.find(Hospital.class,id);
//            List<Appointment> appointmentList = hospital.getAppointments();
//            for (Appointment a:appointmentList) {
//                if (appointment.getId()==a.getId()){
//                    hospital.addAppointment(appointment);
//                }
//            }
            hospital.addAppointment(appointment);
            manager.merge(appointment);
            manager.merge(hospital);


        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("exeption add");

    }

    @Override
    public Appointment getAppointmentById(Long id) {
        try {
            return  manager.find(Appointment.class,id);


        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("exeption get");
        return null;
    }

    @Override
    public void updateAppointment(Long id, Appointment appointment) {
        try {
            System.out.println("1");
            Appointment appointment1 = manager.find(Appointment.class,id);
            appointment1.setData(appointment.getData());
            manager.merge(appointment1);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("exeption up");

    }

    @Override
    public void deleteAppointment(Long id,Long hospitalId) {
        try {
//            Long Hid;
            Hospital hospital = manager.find(Hospital.class,hospitalId);
            Appointment appointment = manager.find(Appointment.class,id);

            for (int a = 0; true  ; a++) {
              if ( hospital.getAppointments().get(a).getId()==id){
                  hospital.getAppointments().remove(a);
                  manager.remove(appointment);
                  break;
              }


            }
//            manager.remove(appointment);
////            appointment.setDoctor(null);
////            appointment.setPatient(null);
////
//            for (int i = 0;true; i++) {
//                Hospital hospitals = manager.find(Hospital.class,i);
//                System.out.println(i);
//                if (hospitals != null){
//                    if (hospitals.getAppointments()!=null && hospitals.getAppointments().size() >= 1) {
//                        System.out.println("p");
////                    List<Appointment> appointments = hospitals.getAppointments();
//                    for (int a = 0; hospitals.getAppointments().get(a).getId() == id  ; a++) {
////                        System.out.println(a);
////                        if (id == appointments.get(a).getId()) {
//                            System.out.println("wasd");
//                            Hid = hospitals.getId();
//                            hospitals.getAppointments().remove(a);
//                            manager.remove(appointment);
//                            break;
////                        }
//                    }
//                }
//
//
//
//                    }
//
//
//            }







        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("exeption delete");

    }
}
