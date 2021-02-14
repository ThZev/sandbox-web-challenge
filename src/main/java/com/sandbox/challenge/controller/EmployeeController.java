package com.sandbox.challenge.controller;

import com.sandbox.challenge.datasource.entitites.EmployeeEntity;
import com.sandbox.challenge.datasource.entitites.SupervisorEntity;
import com.sandbox.challenge.models.Employee;
import com.sandbox.challenge.models.Supervisor;
import com.sandbox.challenge.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/")
    public String showHome(Model model) {
        List<Employee> employeeList = employeeService.listEmployees();
        model.addAttribute("employeeList", employeeList);
        return "index";
    }

    @GetMapping("/employee/create")
    public String showEmployeeRegistrationForm(Model model) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        model.addAttribute("employeeEntity", employeeEntity);
        return "employee/create";
    }

    @PostMapping("/employee/create")
    public String registerEmployee(
            @Valid @ModelAttribute EmployeeEntity employeeEntity,
            BindingResult bindingResult,
            Model model)
    {
        if (bindingResult.hasErrors()) {
            model.addAttribute("employeeEntity", employeeEntity);
            return "employee/create";
        }
        Employee employee = employeeService.createEmployee(employeeEntity);
        model.addAttribute("employee", employee);
        return "employee/created";
    }

    @GetMapping("/employee/update/{id}")
    public String showEmployeeUpdateForm(
            @PathVariable Long id,
            Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "employee/update";
    }

    @PostMapping("/employee/update/{id}")
    public String updateEmployee(
            @PathVariable Long id,
            @Valid Employee employee,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("employee", employee);
            return "employee/update";
        }
        employee = employeeService.updateEmployee(id, employee);
        model.addAttribute("employee", employee);
        return "employee/updated";
    }

    @GetMapping("/supervisor/assign/{id}")
    public String showSupervisorAsignmentForm(
            @PathVariable Long id,
            Model model) {
        SupervisorEntity supervisorEntity = new SupervisorEntity();
        model.addAttribute("supervisorEntity", supervisorEntity);
        model.addAttribute("employeeId",id);
        return "supervisor/assign";
    }

    @PostMapping("/supervisor/assign/{id}")
    public String assignSupervisor(
            @PathVariable Long id,
            @Valid SupervisorEntity supervisorEntity,
            BindingResult bindingResult,
            Model model)
    {
        if (bindingResult.hasErrors()) {
            model.addAttribute("supervisorEntity", supervisorEntity);
            model.addAttribute("employeeId",id);
            return "supervisor/assign";
        }

        Supervisor supervisor = employeeService.createSupervisor(id, supervisorEntity);
        model.addAttribute("supervisor", supervisor);
        model.addAttribute("employeeId",id);
        return "supervisor/assigned";
    }

    @GetMapping("/employee/list")
    public String listEmployees(Model model) {
        List<Employee> employeeList = employeeService.listEmployees();
        model.addAttribute("employeeList", employeeList);
        return "index";
    }

    @GetMapping("/employee/delete/{id}")
    public String deleteEmployee(
            @PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return "index";
    }
}
