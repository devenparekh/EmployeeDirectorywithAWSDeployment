package com.example.demoapplication.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = {"/"})
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {

        this.studentService = studentService;
    }

    @GetMapping()
    public String welCome(){

        return "Welcome to the demo!";
    }

    @GetMapping(path = "api/v1/student")
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
