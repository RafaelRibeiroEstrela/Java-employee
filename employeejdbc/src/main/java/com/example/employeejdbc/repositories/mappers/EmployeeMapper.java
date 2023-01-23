package com.example.employeejdbc.repositories.mappers;

import com.example.employeejdbc.entities.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeMapper implements RowMapper<Employee> {

    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        Employee employee = new Employee();
        employee.setId(rs.getLong("id"));
        employee.setName(rs.getString("name"));
        employee.setCpf(rs.getString("cpf"));
        employee.setAge(rs.getInt("age"));
        employee.setRegister(rs.getInt("register"));
        employee.setSalary(rs.getDouble("salary"));
        return employee;
    }
}
