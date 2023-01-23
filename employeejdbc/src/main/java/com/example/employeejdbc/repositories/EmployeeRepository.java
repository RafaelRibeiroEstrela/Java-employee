package com.example.employeejdbc.repositories;

import com.example.employeejdbc.entities.Employee;

import java.util.List;

public interface EmployeeRepository {

    Employee findById(Long id);

    int save(Employee employee);

    int update(Long id, Employee employee);

    int delete(Long id);

    Employee findByCpf(String cpf);


}
