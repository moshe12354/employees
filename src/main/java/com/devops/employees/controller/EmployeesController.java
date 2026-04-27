package com.devops.employees.controller;

import com.devops.employees.dto.EmployeesDto;
import com.devops.employees.model.EmployeesModel;
import com.devops.employees.service.EmployeesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@Tag(name = "Employee Management", description = "CRUD operations for employees")
public class EmployeesController {

    @Autowired
    private EmployeesService service;

    @PostMapping
    @Operation(summary = "Create a new employee")
    public ResponseEntity<EmployeesModel> createEmployee(@RequestBody EmployeesDto dto) {
        return ResponseEntity.ok(service.createEmployee(dto));
    }

    @GetMapping
    @Operation(summary = "Get all employees")
    public ResponseEntity<List<EmployeesModel>> getAllEmployees() {
        return ResponseEntity.ok(service.getAllEmployees());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get employee by ID")
    public ResponseEntity<EmployeesModel> getEmployeeById(@PathVariable Long id) {
        return service.getEmployeeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing employee")
    public ResponseEntity<EmployeesModel> updateEmployee(@PathVariable Long id, @RequestBody EmployeesDto dto) {
        try {
            return ResponseEntity.ok(service.updateEmployee(id, dto));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an employee")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        service.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}