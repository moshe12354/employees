package com.devops.employees.dto;

import com.devops.employees.model.EmployeesModel;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class EmployeesDto {
    private String name;
    private int age;

    public EmployeesModel toEntity() {
        EmployeesModel employeesModel=new EmployeesModel();
        employeesModel.setName(this.name);
        employeesModel.setAge(this.age);
        return employeesModel;
    }
}
