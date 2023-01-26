package com.example.employeejpa.services;

import com.example.employeejpa.dtos.EmployeeDTO;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDTO> findAll();

    EmployeeDTO findById(Long id);

    void save(EmployeeDTO dto);

    void update(Long id, EmployeeDTO dto);

    void delete(Long id);

}
