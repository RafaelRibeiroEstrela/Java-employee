package com.example.employeejdbc.services.impl;

import com.example.employeejdbc.dtos.EmployeeDTO;
import com.example.employeejdbc.entities.Employee;
import com.example.employeejdbc.repositories.EmployeeRepository;
import com.example.employeejdbc.services.EmployeeService;
import com.example.employeejdbc.services.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository repository;


    @Override
    public EmployeeDTO findById(Long id) {
        Employee employee = repository.findById(id);
        if (employee == null){
            throw new ServiceException("Não foi encontrado um registro com id = " + id);
        }
        return new EmployeeDTO(employee);
    }

    @Override
    public void save(EmployeeDTO dto) {
        Employee employee = repository.findByCpf(dto.getCpf());
        if (employee != null){
            throw new ServiceException("Já existe um empregado com cpf = " + dto.getCpf());
        }
        employee = new Employee();
        copyEmployeeDTOToEmployee(dto, employee);
        repository.save(employee);
    }

    @Override
    public void update(Long id, EmployeeDTO dto) {
        Employee employee = repository.findById(id);
        if (employee == null){
            throw new ServiceException("Não foi encontrado um registro com id = " + id);
        }
        if (!employee.getCpf().equals(dto.getCpf())){
            if (repository.findByCpf(dto.getCpf()) != null){
                throw new ServiceException("Já existe um empregado com cpf = " + dto.getCpf());
            }
        }
        copyEmployeeDTOToEmployee(dto, employee);
        repository.update(id, employee);
    }

    @Override
    public void delete(Long id) {
        Employee employee = repository.findById(id);
        if (employee == null){
            throw new ServiceException("Não foi encontrado um registro com id = " + id);
        }
        repository.delete(id);
    }

    @Override
    public List<EmployeeDTO> findAll() {
        return repository.findAll().stream()
                .map(employee -> new EmployeeDTO(employee))
                .collect(Collectors.toList());
    }

    private void copyEmployeeDTOToEmployee(EmployeeDTO dto, Employee employee){
        employee.setName(dto.getName());
        employee.setAge(dto.getAge());
        employee.setCpf(dto.getCpf());
        employee.setRegister(dto.getRegister());
        employee.setAdmissionDate(dto.getAdmissionDate());
        employee.setResignationDate(dto.getResignationDate());
        employee.setSalary(dto.getSalary());
    }



    private void verifyRules(EmployeeDTO dto){
        if (dto.getAge() < 16){
            throw new ServiceException("A idade deve ser maior ou igual a 16 anos");
        }
    }
}
