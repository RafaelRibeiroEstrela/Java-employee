package com.example.employeejdbc.repositories.impl;

import com.example.employeejdbc.entities.Employee;
import com.example.employeejdbc.repositories.EmployeeRepository;
import com.example.employeejdbc.repositories.mappers.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private String findById = "SELECT * FROM tb_employee WHERE id = ?";
    private String save = "INSERT INTO tb_employee (name, cpf, age, register, admission_date, resignation_date, salary) VALUES (?,?,?,?,?,?,?)";

    private String update = "UPDATE tb_employee SET name = ?, cpf = ?, age = ?, register = ?, admission_date = ?, resignation_date = ?, salary = ? WHERE id = ?";

    private String delete = "DELETE FROM tb_employee WHERE id = ?";

    private String findByCpf = "SELECT * FROM tb_employee WHERE cpf = ?";


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Employee findById(Long id) {
        try{
            return jdbcTemplate.queryForObject(findById, new Object[] { id }, new EmployeeMapper());
        } catch (DataAccessException e){
            return null;
        }
    }

    @Override
    public int save(Employee employee) {
        return jdbcTemplate.update(save, employee.getName(), employee.getCpf(), employee.getAge(),
                employee.getRegister(), employee.getAdmissionDate(), employee.getResignationDate(), employee.getSalary());
    }

    @Override
    public int update(Long id, Employee employee) {
        return jdbcTemplate.update(update, employee.getName(), employee.getCpf(), employee.getAge(),
                employee.getRegister(), employee.getAdmissionDate(), employee.getResignationDate(), employee.getSalary(), id);
    }

    @Override
    public int delete(Long id) {
        return jdbcTemplate.update(delete, id);
    }

    @Override
    public Employee findByCpf(String cpf) {
        try{
            return jdbcTemplate.queryForObject(findByCpf, new Object[] { cpf }, new EmployeeMapper());
        }catch (DataAccessException e){
            return null;
        }

    }


}
