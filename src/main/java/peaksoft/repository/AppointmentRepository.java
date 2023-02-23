package peaksoft.repository;

import peaksoft.model.Appointment;
import peaksoft.model.Patient;

import java.util.List;

public interface AppointmentRepository {
    List<Appointment> getAllAppointments(Long id);

    void addAppointment(Appointment appointment,Long id);

    Appointment getAppointmentById(Long id);

    void updateAppointment(Long id, Appointment appointment);

    void deleteAppointment(Long id,Long hospitalId);
}
