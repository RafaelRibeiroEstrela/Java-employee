package com.example.employeejdbc.repositories.mappers;

import com.example.employeejdbc.entities.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;


public class EmployeeMapper implements RowMapper<Employee> {

    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        Employee employee = new Employee();
        employee.setId(rs.getLong("id"));
        employee.setName(rs.getString("name"));
        employee.setCpf(rs.getString("cpf"));
        employee.setAge(rs.getInt("age"));
        if (rs.getInt("register") == 0){
            employee.setRegister(null);
        } else {
            employee.setRegister(rs.getInt("register"));
        }
        if (rs.getDouble("salary") == 0.0){
            employee.setSalary(null);
        } else {
            employee.setSalary(rs.getDouble("salary"));
        }
        employee.setAdmissionDate(convertToLocalDateViaInstant(rs.getDate("admission_date")));  ;
        employee.setResignationDate(convertToLocalDateViaInstant(rs.getDate("resignation_date")));
        return employee;
    }

    public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        if (dateToConvert == null){
            return null;
        }
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }
}
