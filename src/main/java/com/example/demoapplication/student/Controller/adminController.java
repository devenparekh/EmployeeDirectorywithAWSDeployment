package com.example.demoapplication.student.Controller;

import com.example.demoapplication.student.Service.StudentService;
import com.example.demoapplication.student.definition.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RestController
@RequestMapping(path = {"/admin"})
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class adminController {

    private StudentService studentService;

    @Autowired
    public adminController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(path = "/adminloginSuccess")
    public String welCome(){

        return "Admin Successfully Logged IN! Welcome to Student Application!";
    }

    @GetMapping(path = "/api/v1/student")
    public List<Student> getStudents(){

        return studentService.getStudents();
    }

    @PostMapping(path = "api/v1/student/register")
    public void register(@RequestBody Student student){

        studentService.addNewEntry(student);
    }

    @DeleteMapping(path = "api/v1/student/{studentId}")
    public void removeEntry(@PathVariable ("studentId") Long studentId){
        studentService.deleteEntry(studentId);
    }

    @PutMapping(path = "api/v1/student/{studentId}")
    public void updateStudent(@PathVariable ("studentId") Long studentId,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String email){
        studentService.updateEntry(studentId,name,email);
    }

}
