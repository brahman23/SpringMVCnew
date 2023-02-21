package peaksoft.repository;

import peaksoft.model.Department;

import java.util.List;

public interface DepartmentRepository {
    List<Department> getAllDepartment(Long id);

    void addDepartment(Department department,Long id);

    Department getDepartmentById(Long Id);

    void updateDepartment(Long Id, Department department);

    void deleteDepartment(Long id);
}
