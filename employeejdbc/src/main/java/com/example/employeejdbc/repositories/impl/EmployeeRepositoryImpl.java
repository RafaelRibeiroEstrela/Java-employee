package com.example.employeejdbc.repositories.impl;

import com.example.employeejdbc.entities.Employee;
import com.example.employeejdbc.repositories.EmployeeRepository;
import com.example.employeejdbc.repositories.mappers.EmployeeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final String SAVE = "INSERT INTO tb_employee (name, cpf, age, register, admission_date, resignation_date, salary) VALUES (?,?,?,?,?,?,?)";

    private final String UPDATE = "UPDATE tb_employee SET name=?, cpf=?, age=?, register=?, admission_date=?, resignation_date=?, salary=? WHERE id=?";

    private final String DELETE = "DELETE FROM tb_employee WHERE id = ?";

    private final String FIND_BY_CPF = "SELECT * FROM tb_employee WHERE cpf = ?";


    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public Optional<Employee> findById(Long id) {
        String sql = "SELECT * FROM tb_employee WHERE id = ?";
        return jdbcTemplate.query(sql, new EmployeeMapper(), id)
                .stream().findFirst();
    }

    @Override
    public int save(Employee employee) {
        return jdbcTemplate.update(SAVE, employee.getName(), employee.getCpf(), employee.getAge(),
                employee.getRegister(), employee.getAdmissionDate(), employee.getResignationDate(), employee.getSalary());
    }

    @Override
    public int update(Long id, Employee employee) {
        return jdbcTemplate.update(UPDATE, employee.getName(), employee.getCpf(), employee.getAge(),
                employee.getRegister(), employee.getAdmissionDate(), employee.getResignationDate(), employee.getSalary(), id);
    }

    @Override
    public int delete(Long id) {
        return jdbcTemplate.update(DELETE, id);
    }

    @Override
    public Optional<Employee> findByCpf(String cpf) {
        String sql = "SELECT * FROM tb_employee WHERE cpf = ?";
        return jdbcTemplate.query(sql, new EmployeeMapper(), cpf).stream().findFirst();

    }

    @Override
    public List<Employee> findAll() {
        String sql = "SELECT * FROM tb_employee";
        return jdbcTemplate.query(sql, new EmployeeMapper());
    }


}
