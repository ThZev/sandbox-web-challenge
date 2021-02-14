package com.sandbox.challenge.services;

import com.sandbox.challenge.datasource.entitites.EmployeeEntity;
import com.sandbox.challenge.datasource.entitites.SupervisorEntity;
import com.sandbox.challenge.datasource.repositories.EmployeeRepository;
import com.sandbox.challenge.exception.DatabaseAccessException;
import com.sandbox.challenge.models.Employee;
import com.sandbox.challenge.models.Supervisor;
import org.springframework.stereotype.Service;
import java.util.List;

import static com.sandbox.challenge.datasource.entitites.EmployeeEntity.toEmployeeModel;
import static com.sandbox.challenge.datasource.entitites.EmployeeEntity.toEmployeeModelList;
import static com.sandbox.challenge.datasource.entitites.SupervisorEntity.toSupervisorModel;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeService(
            EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee createEmployee(EmployeeEntity employeeEntity) {
        try {
            employeeRepository.save(employeeEntity);
            return toEmployeeModel(employeeEntity);
        } catch (Exception exception) {
            throw new DatabaseAccessException("Failed to create employee");
        }
    }

    public Employee updateEmployee(Long id, Employee employee) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElse(null);
        try {
            employeeEntity = employeeEntity.updateValues(employee);
            employeeRepository.save(employeeEntity);
            return toEmployeeModel(employeeEntity);
        } catch (Exception exception) {
            throw new DatabaseAccessException("Failed to update employee " + id);
        }
    }

    public Supervisor createSupervisor(Long id, SupervisorEntity supervisorEntity) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElse(null);
        try {
            employeeEntity.setSupervisorEntity(supervisorEntity);
            employeeRepository.save(employeeEntity);
            return toSupervisorModel(supervisorEntity);
        } catch (Exception exception) {
            throw new DatabaseAccessException("Failed to assign supervisor " + supervisorEntity.getId());
        }
    }

    public Employee getEmployeeById(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElse(null);
        try {
            return toEmployeeModel(employeeEntity);
        } catch (Exception exception) {
            throw new DatabaseAccessException("Failed to find employee " + id);
        }
    }

    public List<Employee> listEmployees() {
        List<EmployeeEntity> employeeEntityList = employeeRepository.findAll();
        try {
            return toEmployeeModelList(employeeEntityList);
        } catch (Exception exception) {
            throw new DatabaseAccessException("Failed to list employees");
        }
    }

    public Employee deleteEmployee(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElse(null);
        try {
            employeeRepository.delete(employeeEntity);
            return toEmployeeModel(employeeEntity);
        } catch (Exception exception) {
            throw new DatabaseAccessException("Failed to delete employee " + id);
        }
    }
}
