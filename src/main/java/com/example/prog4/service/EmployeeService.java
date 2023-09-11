package com.example.prog4.service;


import com.example.prog4.model.domain.Employee;
import com.example.prog4.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository repository;

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    public Employee getById(int id) {
        return repository.findById(id).orElse(null);
    }

    public void crupdateEmployee(Employee employee) {
        repository.save(employee);
    }

    public List<Employee> filterByFirstName(String firstName) {
        return repository.findByFirstNameContainingIgnoreCase(firstName);
    }

    public List<Employee> filterByLastName(String lastName) {
        return repository.findByLastNameContainingIgnoreCase(lastName);
    }

    public List<Employee> filterBySex(String sex) {
        return repository.findBySex(sex);
    }

    public List<Employee> filterByFunction(String function) {
        return repository.findByFunctionContainingIgnoreCase(function);
    }

    public List<Employee> filterByHireDateBetween(LocalDate hireDateStart, LocalDate hireDateEnd) {
        return repository.findByHireDateBetween(hireDateStart, hireDateEnd);
    }

    public List<Employee> filterByRetireDateBetween(LocalDate retireDateStart, LocalDate retireDateEnd) {
        return repository.findByRetireDateBetween(retireDateStart, retireDateEnd);
    }

    public List<Employee> filterByCriteria(String firstName, String lastName, String sex, String function, LocalDate hireDateStart, LocalDate hireDateEnd, LocalDate retireDateStart, LocalDate retireDateEnd) {
        List<Employee> employees = new ArrayList<>();
        employees.addAll(repository.findByCriteria(firstName, lastName, sex, function, hireDateStart, hireDateEnd, retireDateStart, retireDateEnd));
        return employees;
    }
}