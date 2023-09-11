package com.example.prog4.model.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;

    private String lastName;

    private String birthDate;

    @Column(length = 10000000)
    private String photo;

    @Column(unique = true)
    private String serialNumber;

    @PostPersist
    private void generateSerialNumber() {
        this.serialNumber = "EMP-" + String.format("%03d", this.getId());
    }

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @OneToMany
    private List<PhoneNumber> phoneNumbers;

    private String address;

    @Column(unique = true)
    private String personalEmail;
    @Column(unique = true)
    private String proEmail;

    @Column(unique = true)
    private String cinNumber;
    private LocalDate cinDeliverDate;
    private String cinDeliverPlace;

    private String function;

    private int childrenNumber;

    private LocalDate hireDate;
    private LocalDate retireDate;

    private String category;

    @Column(unique = true)
    private String CNAPSNumber;
    public enum Sex {
        H,F
    }
}