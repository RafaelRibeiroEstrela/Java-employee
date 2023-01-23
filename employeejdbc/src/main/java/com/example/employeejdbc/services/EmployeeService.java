package com.example.employeejdbc.services;

import com.example.employeejdbc.dtos.EmployeeDTO;
import com.example.employeejdbc.entities.Employee;

public interface EmployeeService {

    EmployeeDTO findById(Long id);

    void save(EmployeeDTO dto);

    void update(Long id, EmployeeDTO dto);

    void delete(Long id);
}
