package com.example.prog4.service;


import com.example.prog4.model.domain.PhoneNumber;
import com.example.prog4.repository.PhoneNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneNumberService {
    @Autowired
    private PhoneNumberRepository repository;

//    public List<PhoneNumber> getAllPhoneNumbers() {
//        return repository.findAll();
//    }
//
//    public PhoneNumber getById(int id) {
//        return repository.findById(id).orElse(null);
//    }
//
    public void crupdatePhoneNumber(PhoneNumber number) {
        repository.save(number);
    }

}