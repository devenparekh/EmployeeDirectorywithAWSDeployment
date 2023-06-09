package com.example.demoapplication.student.Controller;

import com.example.demoapplication.student.definition.Student;
import com.example.demoapplication.student.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@RestController
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

    private StudentService studentService;

    @Autowired
    public UserController(StudentService studentService) {

        this.studentService = studentService;
    }

    @GetMapping(path = "/loginSuccess")
    public String welCome(){

        return "User Successfully Logged IN! Welcome to Student Application!";
    }

    @GetMapping(path = "api/v1/student/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable ("studentId") Long studentId){

            Student studentById = new Student();
            studentById = studentService.getStudentById(studentId);

            return ResponseEntity.ok(studentById);
    }

    @PostMapping(path = "api/v1/student/register")
    public ResponseEntity<Student> register(@RequestBody Student student){

        studentService.addNewEntry(student);

        return ResponseEntity.ok(student);
    }

    @DeleteMapping(path = "api/v1/student/{studentId}")
    public ResponseEntity<String> removeEntry(@PathVariable ("studentId") Long studentId){

        studentService.deleteEntry(studentId);

        return ResponseEntity.ok("Entry Deleted!");
    }

    @PutMapping(path = "api/v1/student/{studentId}")
    public void updateStudent(@PathVariable ("studentId") Long studentId,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String email){
        studentService.updateEntry(studentId,name,email);
    }
}