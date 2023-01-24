package com.example.employeejdbc.services;

import com.example.employeejdbc.dtos.EmployeeDTO;
import com.example.employeejdbc.entities.Employee;

import java.util.List;

public interface EmployeeService {

    EmployeeDTO findById(Long id);

    void save(EmployeeDTO dto);

    void update(Long id, EmployeeDTO dto);

    void delete(Long id);

    List<EmployeeDTO> findAll();
}
