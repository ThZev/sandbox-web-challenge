package com.sandbox.challenge.datasource.entitites;

import com.sandbox.challenge.models.Supervisor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class SupervisorEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    @NotBlank(message="Name required")
    private String firstName;

    @Column
    @NotBlank(message="Surname required")
    private String lastName;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date createDate;

    @OneToMany(mappedBy = "supervisorEntity", cascade = CascadeType.ALL)
    private List<EmployeeEntity> employeeList = new ArrayList<>();

    public static List<Supervisor> toSupervisorModelList(List<SupervisorEntity> supervisorEntityList) {
        List<Supervisor> supervisorList = new ArrayList<>();
        for (SupervisorEntity supervisorEntity : supervisorEntityList) {
            supervisorList.add(
                    toSupervisorModel(supervisorEntity)
            );
        }
        return supervisorList;
    }

    public static Supervisor toSupervisorModel(SupervisorEntity supervisorEntity){
        return new Supervisor(
                supervisorEntity.getFirstName(),
                supervisorEntity.getLastName()
        );
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public List<EmployeeEntity> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<EmployeeEntity> employeeList) {
        this.employeeList = employeeList;
    }
}
