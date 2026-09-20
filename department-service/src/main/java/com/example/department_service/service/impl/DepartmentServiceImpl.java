package com.example.department_service.service.impl;

import com.example.department_service.dto.DepartmentDto;
import com.example.department_service.entity.Department;
import com.example.department_service.repository.DepartmentRepository;
import com.example.department_service.service.DepartmentService;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        Department department = new Department(
                departmentDto.getId(),
                departmentDto.getDepartmentName(),
                departmentDto.getDepartmentDescription(),
                departmentDto.getDepartmentCode()
        );
        Department department1 = departmentRepository.save(department);

        DepartmentDto savedDepartmentDto = new DepartmentDto(
                department1.getId(),
                department1.getDepartmentName(),
                department1.getDepartmentDescription(),
                department1.getDepartmentCode()
        );

        return savedDepartmentDto;
    }

    @Override
    public DepartmentDto getDepartmentByCode(String code) {
       Department department = departmentRepository.findByDepartmentCode(code);
       DepartmentDto departmentDto = new DepartmentDto(
               department.getId(),
               department.getDepartmentName(),
               department.getDepartmentDescription(),
               department.getDepartmentCode()
       );
        return departmentDto;
    }
}
