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

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final String FIND_BY_ID = "SELECT * FROM tb_employee WHERE id = ?";
    private final String SAVE = "INSERT INTO tb_employee (name, cpf, age, register, admission_date, resignation_date, salary) VALUES (?,?,?,?,?,?,?)";

    private final String UPDATE = "UPDATE tb_employee SET name=?, cpf=?, age=?, register=?, admission_date=?, resignation_date=?, salary=? WHERE id=?";

    private final String DELETE = "DELETE FROM tb_employee WHERE id = ?";

    private final String FIND_BY_CPF = "SELECT * FROM tb_employee WHERE cpf = ?";

    private final String FIND_ALL = "SELECT * FROM tb_employee";


    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public Employee findById(Long id) {
        try{
            return jdbcTemplate.queryForObject(FIND_BY_ID, new Object[] { id }, new EmployeeMapper());
        } catch (DataAccessException e){
            return null;
        }
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
    public Employee findByCpf(String cpf) {
        try{
            return jdbcTemplate.queryForObject(FIND_BY_CPF, new Object[] { cpf }, new EmployeeMapper());
        }catch (DataAccessException e){
            return null;
        }
    }

    @Override
    public List<Employee> findAll() {
        try{
            return jdbcTemplate.query(FIND_ALL, new EmployeeMapper());
        }catch (DataAccessException e){
            return new ArrayList<>();
        }
    }


}
