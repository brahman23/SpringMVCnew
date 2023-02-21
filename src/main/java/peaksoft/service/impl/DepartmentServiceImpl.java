package peaksoft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.model.Department;
import peaksoft.repository.DepartmentRepository;
import peaksoft.service.DepartmentService;

import java.util.List;
@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<Department> getAllDepartment(Long id) {
        try {
            return departmentRepository.getAllDepartment(id);
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
        return null;

    }

    @Override
    public void addDepartment(Department department, Long id) {
        departmentRepository.addDepartment(department,id);
    }

    @Override
    public Department getDepartmentById(Long Id) {
        return departmentRepository.getDepartmentById(Id);
    }

    @Override
    public void updateDepartment(Long Id, Department department) {
        System.out.println(2);
        departmentRepository.updateDepartment(Id, department);
    }

    @Override
    public void deleteDepartment(Long id) {
        try {
            System.out.println(33);
            departmentRepository.deleteDepartment(id);
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }


    }
}
