package com.example.employee_service.service.impl;

import com.example.employee_service.dto.EmployeeDto;
import com.example.employee_service.entity.Employee;
import com.example.employee_service.repository.EmployeeRepository;
import com.example.employee_service.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

        Employee employee = new Employee(
                employeeDto.getId(),
                employeeDto.getFirstname(),
                employeeDto.getLastname(),
                employeeDto.getEmail()
        );
        Employee savedEmployee = employeeRepository.save(employee);

        EmployeeDto savedEmployeeDto = new EmployeeDto(
                savedEmployee.getId(),
                savedEmployee.getFirstname(),
                savedEmployee.getLastname(),
                savedEmployee.getEmail()
        );
        return savedEmployeeDto;
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).get();
        EmployeeDto employeeDto = new EmployeeDto(
                employee.getId(),
                employee.getFirstname(),
                employee.getLastname(),employee.getEmail()
        );
        return employeeDto;
    }
}