package com.example.employeejdbc;

import com.example.employeejdbc.entities.Employee;
import com.example.employeejdbc.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class EmployeejdbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeejdbcApplication.class, args);
	}

}
