package com.example.demoapplication.student.Controller;

import com.example.demoapplication.student.definition.Student;
import com.example.demoapplication.student.Service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;
import java.util.List;


@RestController
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "User Controller", description = "Rest Api related to User Controller")
public class UserController {

    private StudentService studentService;

    @Autowired
    public UserController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(path = "/api/v1/getAllStudents")
    public List<Student> getStudents(){
        return studentService.getStudents();
    }

    @GetMapping(path = "api/v1/student/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable ("studentId") Long studentId){
            Student studentById;
            studentById = studentService.getStudentById(studentId);
            return ResponseEntity.ok(studentById);
    }

    @PostMapping(path = "api/v1/student/register")
    public ResponseEntity<Student> register(@RequestBody Student student){
        studentService.addNewEntry(student);
        return ResponseEntity.ok(student);
    }

    @PutMapping(path = "api/v1/student/{studentId}")
    public ResponseEntity<Student> updateStudent(@PathVariable ("studentId") Long studentId,
                                                 @RequestParam(required = false) String name,
                                                 @RequestParam(required = false) String email,
                                                 @RequestParam(required = false) String dob){
        Student s = studentService.updateEntry(studentId,name,email,dob);
        return ResponseEntity.ok(s);
    }

    @DeleteMapping(path = "api/v1/student/{studentId}")
    public void removeEntry(@PathVariable ("studentId") Long studentId){
        studentService.deleteEntry(studentId);
    }

}
