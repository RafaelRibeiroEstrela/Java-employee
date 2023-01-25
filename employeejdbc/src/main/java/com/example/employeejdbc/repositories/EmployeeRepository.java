package com.example.employeejdbc.repositories;

import com.example.employeejdbc.entities.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {

    Optional<Employee> findById(Long id);

    int save(Employee employee);

    int update(Long id, Employee employee);

    int delete(Long id);

    Optional<Employee> findByCpf(String cpf);

    List<Employee> findAll();


}
