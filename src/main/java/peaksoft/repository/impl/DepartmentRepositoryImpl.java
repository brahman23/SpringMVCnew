package peaksoft.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.model.Appointment;
import peaksoft.model.Department;
import peaksoft.model.Doctor;
import peaksoft.model.Hospital;
import peaksoft.repository.DepartmentRepository;

import java.io.IOException;
import java.util.List;
@Repository
@Transactional
public class DepartmentRepositoryImpl implements DepartmentRepository {
    @PersistenceContext
    private final EntityManager entityManager;
    @Autowired
    public DepartmentRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Department> getAllDepartment(Long id) {
        return entityManager.createQuery("select d from Department d where d.hospital.id = :id", Department.class).setParameter("id", id).getResultList();
    }
    @Override
    public List<Department> getAllDepartmentList(Long id) {
        return entityManager.createQuery("select d from Department d where d.hospital.id = :id", Department.class).setParameter("id", id).getResultList();
    }


    @Override
    public void addDepartment(Department department, Long id) {
        Hospital hospital =entityManager.find(Hospital.class,id);
        hospital.addDepartment(department);
        department.setHospital(hospital);
        entityManager.merge(department);

    }

    @Override
    public Department getDepartmentById(Long Id) {
        return entityManager.find(Department.class,Id);
    }

    @Override
    public void updateDepartment(Long Id, Department department) {
        Department department11 = entityManager.find(Department.class,Id);
        department11.setName(department.getName());
        System.out.println(1);

        entityManager.merge(department11);

    }

    @Override
    public void deleteDepartment(Long id) {
        try {
            System.out.println(111);
            Department department = entityManager.find(Department.class, id);
//        department.setHospital(null);
            entityManager.remove(department);
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }




    }
    @Override
     public void assignDepartment(Long doctorId, Long departmentId) throws IOException {
        Department department = entityManager.find(Department.class, departmentId);
        Doctor doctor = entityManager.find(Doctor.class, doctorId);
        if (doctor.getDepartments() != null) {
            for (Department d : doctor.getDepartments()) {
                if (d.getId() == departmentId) {
                    throw new IOException("myndai group bar!!!");
                }
            }
        }
        department.addDoctor(doctor);
        doctor.addDepartment(department);
        entityManager.merge(department);
        entityManager.merge(doctor);

    }


    @Override
    public void assignDepartmentToAppointment(Long appointmentId, Long departmentId) throws IOException {
        Department department = entityManager.find(Department.class,departmentId);
        Appointment appointment = entityManager.find(Appointment.class,appointmentId);
        if (appointment.getDepartment() != null) {
            Department d = appointment.getDepartment();
            appointment.setDepartment(department);
            entityManager.merge(department);
            entityManager.merge(appointment);

    }
    }
}
