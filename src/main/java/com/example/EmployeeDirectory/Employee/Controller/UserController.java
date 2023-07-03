package com.example.EmployeeDirectory.Employee.Controller;

import com.example.EmployeeDirectory.Employee.Definition.Employee;
import com.example.EmployeeDirectory.Employee.Service.EmployeeService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;


@RestController
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "User Controller", description = "Rest Api related to User Controller")
public class UserController {

    private EmployeeService employeeService;

    @Autowired
    public UserController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/api/v1/getAllEmployees")
    public List<Employee> getEmployees(){
        try {
            List<Employee> employeeList;
            employeeList = employeeService.getEmployees();
            if(employeeList != null){
                return employeeList;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    return null;
    }

    @GetMapping(path = "api/v1/Employee/{employeeId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable ("employeeId") Long employeeId){
          try{
              Employee employeeById;
              employeeById = employeeService.getEmployeeById(employeeId);
              return ResponseEntity.ok(employeeById);
          }
          catch (Exception e) {
              e.printStackTrace();
          }
           return null;
    }

    @PostMapping(path = "api/v1/Employee/register")
    public ResponseEntity<Employee> register(@RequestBody Employee employee){
       try{
           employeeService.addNewEntry(employee);
           return ResponseEntity.ok(employee);
       }
       catch (Exception e){
           e.printStackTrace();
       }
       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(employee);
    }

    @PutMapping(path = "api/v1/Employee/{EmployeeId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable ("EmployeeId") Long employeeId,
                                                 @RequestParam(required = false) String name,
                                                 @RequestParam(required = false) String email,
                                                 @RequestParam(required = false) String dob){
       Employee updatedEmployee = null;
        try{
            updatedEmployee = employeeService.updateEntry(employeeId,name,email,dob);
            return ResponseEntity.ok(updatedEmployee);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(updatedEmployee);
    }

    @DeleteMapping(path = "api/v1/Employee/{EmployeeId}")
    public void removeEntry(@PathVariable ("EmployeeId") Long EmployeeId) {
        try {
            employeeService.deleteEntry(EmployeeId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
