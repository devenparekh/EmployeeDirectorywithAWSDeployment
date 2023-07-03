package com.example.EmployeeDirectory.Employee.Definition;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;


@Entity
@Table(name = "Employee")
public class Employee {
    @Id
    @SequenceGenerator(
            name = "employee_sequence",
            sequenceName = "employee_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "employee_sequence"
    )
    @ApiModelProperty(notes = "Id of Employee", name = "Id", value = "test Id")
    private Long id;

    @ApiModelProperty(notes = "Name of Employee", name = "Name", value = "test name")
    private String name;

    @ApiModelProperty(notes = "Email of Employee", name = "Email", value = "test Email")
    private String email;

    @ApiModelProperty(notes = "DOB of Employee", name = "DOB", value = "test DOB")
    private LocalDate dob;

    @ApiModelProperty(notes = "Age of Employee", name = "Age", value = "test Age")
    private Integer age;

    public Employee() {
    }

    public Employee(Long id, String name, String email, LocalDate dob ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    public Employee(String name, String email, LocalDate dob) {
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return Period.between(this.dob,LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString(){
        return "Student{" + "id="+ id + ",name="+ name + '\''+ ",email=" + email + '\'' +",dob=" + dob+",age=" + age +"}";
    }
}
