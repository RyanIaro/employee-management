package com.example.prog4.mapper;

import com.example.prog4.model.domain.Employee;
import com.example.prog4.model.rest.RestEmployee;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class EmployeeMapper {

    public Employee toDomain(RestEmployee employee){
        return Employee.builder()
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .birthDate(employee.getBirthDate())
                .serialNumber(employee.getSerialNumber())
                .sex(Employee.Sex.valueOf(employee.getSex().toString()))
                .address(employee.getAddress())
                .proEmail(employee.getProEmail())
                .personalEmail(employee.getPersonalEmail())
                .cinNumber(employee.getCinNumber())
                .cinDeliverDate(employee.getCinDeliverDate())
                .cinDeliverPlace(employee.getCinDeliverPlace())
                .function(employee.getFunction())
                .childrenNumber(employee.getChildrenNumber())
                .hireDate(employee.getHireDate())
                .retireDate(employee.getRetireDate())
                .category(employee.getCategory())
                .CNAPSNumber(employee.getCNAPSNumber())
                .build();
    }
}
