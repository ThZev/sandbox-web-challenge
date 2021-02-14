package com.sandbox.challenge.datasource.entitites;

import com.sandbox.challenge.models.Employee;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class EmployeeEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    @NotBlank(message="Name required")
    private String firstName;

    @Column
    @NotBlank(message="Surname required")
    private String lastName;

    @Column
    @NotBlank(message="Position required")
    private String position;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date createDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private SupervisorEntity supervisorEntity;

    public static List<Employee> toEmployeeModelList(List<EmployeeEntity> employeeEntityList) {
        List<Employee> employeeList = new ArrayList<>();
        for (EmployeeEntity employeeEntity : employeeEntityList) {
            employeeList.add(
                    toEmployeeModel(employeeEntity)
            );
        }
        return employeeList;
    }

    public static Employee toEmployeeModel(EmployeeEntity employeeEntity){
        String supervisor = "";
        if(employeeEntity.getSupervisorEntity()!=null){
            supervisor = employeeEntity.getSupervisorEntity().getFirstName()+" "
                    +employeeEntity.getSupervisorEntity().getLastName();
        }
        return new Employee(
                employeeEntity.getId(),
                employeeEntity.getFirstName(),
                employeeEntity.getLastName(),
                employeeEntity.getPosition(),
                supervisor
        );
    }

    public EmployeeEntity updateValues(Employee employee){
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.position = employee.getPosition();
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public SupervisorEntity getSupervisorEntity() {
        return supervisorEntity;
    }

    public void setSupervisorEntity(SupervisorEntity supervisorEntity) {
        this.supervisorEntity = supervisorEntity;
    }
}
