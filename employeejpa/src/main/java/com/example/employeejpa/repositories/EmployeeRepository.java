package com.example.employeejpa.repositories;

import com.example.employeejpa.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findByCpf(String cpf);

}
