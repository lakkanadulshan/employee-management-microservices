package com.example.department_service.controller;

import com.example.department_service.dto.DepartmentDto;
import com.example.department_service.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<DepartmentDto> savedDepartment(@RequestBody DepartmentDto departmentDto){
        DepartmentDto saveDepartment = departmentService.saveDepartment(departmentDto);
        return new ResponseEntity<>(saveDepartment, HttpStatus.CREATED);

    }

    @GetMapping("{department-code}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable(value = "department-code")String departmentCode){
        DepartmentDto getDepartmentDto = departmentService.getDepartmentByCode(departmentCode);
        return  new ResponseEntity<>(getDepartmentDto,HttpStatus.OK);
    }
}
