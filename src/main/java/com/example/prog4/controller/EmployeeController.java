package com.example.prog4.controller;
import com.example.prog4.mapper.EmployeeMapper;
import com.example.prog4.model.domain.Employee;
import com.example.prog4.model.domain.PhoneNumber;
import com.example.prog4.model.rest.RestEmployee;
import com.example.prog4.service.EmployeeService;
import com.example.prog4.service.PhoneNumberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Controller
@AllArgsConstructor
public class EmployeeController {

    private final PhoneNumberService phoneNumberService;
    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    @GetMapping(value = "/")
    public String showEmployeeList(
            @RequestParam(required = false, defaultValue = "") String firstName,
            @RequestParam(required = false, defaultValue = "") String lastName,
            @RequestParam(required = false, defaultValue = "") String sex,
            @RequestParam(required = false, defaultValue = "") String function,
            @RequestParam(required = false, defaultValue = "") LocalDate hireDateStart,
            @RequestParam(required = false, defaultValue = "") LocalDate hireDateEnd,
            @RequestParam(required = false, defaultValue = "") LocalDate retireDateStart,
            @RequestParam(required = false, defaultValue = "") LocalDate retireDateEnd,
            Model model
    ) {
        List<Employee> filteredEmployees = employeeService.filterByCriteria(firstName, lastName, sex, function, hireDateStart, hireDateEnd, retireDateStart, retireDateEnd);
        if (filteredEmployees.isEmpty()){
            filteredEmployees = employeeService.getAllEmployees();
        }

        model.addAttribute("employees", filteredEmployees);
        return "employee-list";
    }

    @GetMapping("/details/{employeeId}")
    public String showEmployeeDetails(
            Model model,
            @PathVariable int employeeId
    ) {
        model.addAttribute("employee", employeeService.getById(employeeId));
        return "employee-details";
    }

    @GetMapping(value = "/addEmployee")
    public String showAddEmployeeForm(Model model) {
        model.addAttribute("newEmployee",new Employee());
        return "add-employee-form";
    }

    @PostMapping("/addEmployee")
    public String addEmployee(
            @ModelAttribute("newEmployee") RestEmployee toAdd,
            @RequestParam("photo") MultipartFile photo
    ) throws IOException {
        String photoString = null;
        if(!photo.isEmpty()) {
            photoString = Base64.getEncoder().encodeToString(toAdd.getPhoto().getBytes());
        }

        String numbersInput = toAdd.getPhoneNumbers();
        List<String> numbersList = Arrays.asList(numbersInput.split(";"));

        Employee employee = employeeMapper.toDomain(toAdd);

        List<PhoneNumber> phoneNumbers = new ArrayList<>();
        for (String phoneNumberStr : numbersList) {
            PhoneNumber phoneNumber = new PhoneNumber();
            phoneNumber.setValue(phoneNumberStr);
            phoneNumber.setEmployee(employee);
            phoneNumbers.add(phoneNumber);
        }

        employee.setPhoto(photoString);
        employeeService.crupdateEmployee(employee);
        for (PhoneNumber phoneNumber: phoneNumbers) {
            phoneNumberService.crupdatePhoneNumber(phoneNumber);
        }
        return "redirect:/";
    }

    @GetMapping("/update/{employeeId}")
    public String showUpdateEmployeeForm(
            Model model,
            @PathVariable int employeeId
    ) {
        model.addAttribute("employee", employeeService.getById(employeeId));
        return "update-employee-form";
    }

    @PostMapping("/update/{employeeId}")
    public String updateEmployee(
            @ModelAttribute("employee") RestEmployee current,
            @PathVariable int employeeId,
            @RequestParam("photo") MultipartFile photo
    ) throws IOException {
        Employee toUpdate = employeeService.getById(employeeId);
        if(toUpdate == null) {
            return "redirect:/notFound";
        }

        String photoString = null;
        if(!photo.isEmpty()) {
            photoString = Base64.getEncoder().encodeToString(photo.getBytes());
        }

        toUpdate.setFirstName(current.getFirstName());
        toUpdate.setLastName(current.getLastName());
        toUpdate.setBirthDate(current.getBirthDate());
        toUpdate.setPhoto(photoString);
        toUpdate.setSex(current.getSex());
        toUpdate.setProEmail(current.getProEmail());
        toUpdate.setPersonalEmail(current.getPersonalEmail());
        toUpdate.setCinNumber(current.getCinNumber());
        toUpdate.setCinDeliverDate(current.getCinDeliverDate());
        toUpdate.setCinDeliverPlace(current.getCinDeliverPlace());
        toUpdate.setFunction(current.getFunction());
        toUpdate.setChildrenNumber(current.getChildrenNumber());
        toUpdate.setHireDate(current.getHireDate());
        toUpdate.setRetireDate(current.getRetireDate());
        toUpdate.setCNAPSNumber(current.getCNAPSNumber());

        String numbersInput = current.getPhoneNumbers();
        List<String> numbersList = Arrays.asList(numbersInput.split(";"));
        List<PhoneNumber> phoneNumbers = new ArrayList<>();
        for (String phoneNumberStr : numbersList) {
            PhoneNumber phoneNumber = new PhoneNumber();
            phoneNumber.setValue(phoneNumberStr);
            phoneNumber.setEmployee(toUpdate);
            phoneNumbers.add(phoneNumber);
            phoneNumberService.crupdatePhoneNumber(phoneNumber);
        }

        toUpdate.setPhoneNumbers(phoneNumbers);

        employeeService.crupdateEmployee(toUpdate);
        return "redirect:/details/{employeeId}";
    }

    @GetMapping("/notFound")
    public String notFound() {
        return "not-found";
    }
}