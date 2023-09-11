package com.example.prog4.model.rest;


import com.example.prog4.model.domain.Employee;
import com.example.prog4.model.domain.PhoneNumber;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestEmployee {
    private String firstName;

    private String lastName;

    private String birthDate;

    private MultipartFile photo;

    private String serialNumber;

    private Employee.Sex sex;

    private String phoneNumbers;

    private String address;

    private String personalEmail;
    private String proEmail;

    private String cinNumber;
    private LocalDate cinDeliverDate;
    private String cinDeliverPlace;

    private String function;

    private int childrenNumber;

    private LocalDate hireDate;
    private LocalDate retireDate;

    private String category;

    private String CNAPSNumber;
}