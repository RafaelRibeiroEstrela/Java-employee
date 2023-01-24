package com.example.employeejdbc.dtos;

import com.example.employeejdbc.entities.Employee;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

public class EmployeeDTO implements Serializable {

    private static long serialVersionUID = 1L;

    private Long id;

    @NotBlank(message = "O NOME é obrigatório")
    private String name;

    @NotBlank(message = "O CPF é obrigatório")
    private String cpf;

    @NotNull(message = "A IDADE é obrigatória")
    private Integer age;

    private Integer register;


    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate admissionDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate resignationDate;

    private Double salary;

    public EmployeeDTO(){

    }

    public EmployeeDTO(Employee employee){
        this.id = employee.getId();
        this.name = employee.getName();
        this.cpf = employee.getCpf();
        this.age = employee.getAge();
        this.register = employee.getRegister();
        this.admissionDate = employee.getAdmissionDate();
        this.resignationDate = employee.getResignationDate();
        this.salary = employee.getSalary();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getRegister() {
        return register;
    }

    public void setRegister(Integer register) {
        this.register = register;
    }

    public LocalDate getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(LocalDate admissionDate) {
        this.admissionDate = admissionDate;
    }

    public LocalDate getResignationDate() {
        return resignationDate;
    }

    public void setResignationDate(LocalDate resignationDate) {
        this.resignationDate = resignationDate;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
