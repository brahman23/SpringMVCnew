package peaksoft.service;

import peaksoft.model.Department;
import peaksoft.model.Doctor;

import java.util.List;

public interface DepartmentService {
    List<Department> getAllDepartment(Long id);

    void addDepartment(Department department,Long id);

    Department getDepartmentById(Long Id);

    void updateDepartment(Long Id, Department department);

    void deleteDepartment(Long id);
}
