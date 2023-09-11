package com.example.prog4.repository;

import com.example.prog4.model.domain.Employee;
import com.example.prog4.model.domain.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Integer> {
    public abstract List<PhoneNumber> getPhoneNumbersByEmployee(Employee employee);
}
