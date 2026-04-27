package com.devops.employees.service;

import com.devops.employees.dto.EmployeesDto;
import com.devops.employees.model.EmployeesModel;
import com.devops.employees.repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeesService {

    @Autowired
    private EmployeesRepository repository;

    public EmployeesModel createEmployee(EmployeesDto dto) {
        EmployeesModel employee = dto.toEntity();
        return repository.save(employee);
    }
    public List<EmployeesModel> getAllEmployees() {
        return repository.findAll();
    }
    public Optional<EmployeesModel> getEmployeeById(Long id) {
        return repository.findById(id);
    }
    public EmployeesModel updateEmployee(Long id, EmployeesDto dto) {
        return repository.findById(id).map(employee -> {
            employee.setName(dto.getName());
            employee.setAge(dto.getAge());
            return repository.save(employee);
        }).orElseThrow(() -> new RuntimeException("Employee not found with id " + id));
    }
    public void deleteEmployee(Long id) {
        repository.deleteById(id);
    }
}