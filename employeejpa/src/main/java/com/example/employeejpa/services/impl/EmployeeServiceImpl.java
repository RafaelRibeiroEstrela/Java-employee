package com.example.employeejpa.services.impl;

import com.example.employeejpa.dtos.EmployeeDTO;
import com.example.employeejpa.entities.Employee;
import com.example.employeejpa.repositories.EmployeeRepository;
import com.example.employeejpa.services.EmployeeService;
import com.example.employeejpa.services.exceptions.DatabaseException;
import com.example.employeejpa.services.exceptions.ResourceNotFoundException;
import com.example.employeejpa.services.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Override
    public List<EmployeeDTO> findAll() {
        return repository.findAll().stream()
                .map(obj -> new EmployeeDTO(obj))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO findById(Long id) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Registro não encontrado com id = " + id));
        return new EmployeeDTO(employee);
    }

    @Override
    public void save(EmployeeDTO dto) {
        verifyRules(dto);
        if (repository.findByCpf(dto.getCpf()) != null){
            throw new ServiceException("Já existe o cpf " + dto.getCpf() + " na base de dados.");
        }
        Employee employee = new Employee();
        copyDTOToEntity(dto, employee);
        repository.save(employee);
    }

    @Override
    public void update(Long id, EmployeeDTO dto) {
        verifyRules(dto);
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Registro não encontrado com id = " + id));
        if (!dto.getCpf().equals(employee.getCpf())){
            if (repository.findByCpf(dto.getCpf()) != null){
                throw new ServiceException("Já existe o cpf " + dto.getCpf() + " na base de dados.");
            }
        }
        copyDTOToEntity(dto, employee);
        repository.save(employee);

    }

    @Override
    public void delete(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Registro não encontrado com id = " + id));
        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }

    private void copyDTOToEntity(EmployeeDTO dto, Employee employee){
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
