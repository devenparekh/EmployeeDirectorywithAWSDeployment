package com.example.demoapplication.student.studentService;

import com.example.demoapplication.student.studentdefinition.Student;
import com.example.demoapplication.student.studentRepository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository)
    {
        this.studentRepository = studentRepository;
    }

    public void addNewEntry(Student student) {
        Optional<Student> entry = studentRepository.findStudentByEmail(student.getEmail());
        Optional<Student> entryByName = studentRepository.findStudentByName(student.getName());

        System.out.println("*************Inside addEntry Method**********");

            if(entry.isPresent()){
            System.out.println("Email Id is already present. Use Another Email or go to Login.");
            throw new IllegalStateException("Email Already present");
            }

            else if (entryByName.isPresent()){
            System.out.println("Name provided is already present and cannot be reused. Go to Login or use different Name.");
            throw new IllegalStateException("Name Already present");
            }

            else{
            studentRepository.save(student);
            System.out.println("Saving Student Details to Database Successful!");
            }

        System.out.println("**********Exiting AddEntry Method***********");
    }

    public List<Student> getStudents(){
        System.out.println("*************Inside getStudents Method**********");

            List<Student> studentList = studentRepository.findAll();

            System.out.println("Successfully fetched the list : " + studentList);

            System.out.println("*************Exiting getStudents Method**********");

        return studentList;
    }

    public void deleteEntry(Long studentId) {
    System.out.println("************* Inside deleteEntry Method **********");

    boolean exists = studentRepository.existsById(studentId);

        if (!exists){
        System.out.println("Student cannot be found for the given ID");
        throw new IllegalStateException("Id not present.");
        }

        else {
        System.out.println("Deleting Student Details for the ID : " + studentId);
        studentRepository.deleteById(studentId);
        }

    System.out.println("************* Exiting deleteEntry Method **********");
    }

    @Transactional
    public void updateEntry(Long studentId, String name, String email) {
        System.out.println("************* Inside updateEntry Method **********");

        Student student = studentRepository.findById(studentId)
                .orElseThrow(()-> new IllegalStateException("Student ID not found."));

            if (name != null && name.length()>0 && !student.getName().equals(name)){
                student.setName(name);
                System.out.println("Name updated from " + student.getName() + " to " + name);
            }

            if (email != null && email.length()>0 && !student.getEmail().equals(email)){

                Optional<Student> optionalStudentEmail = studentRepository.findStudentByEmail(email);

                    if (optionalStudentEmail.isPresent()){
                        throw new IllegalStateException("Email already present.");
                    }
                student.setEmail(email);
                System.out.println("Email updated from " + student.getEmail() + " to " + email);
        }
        System.out.println("************* Exiting updateEntry Method **********");
    }

    public List<Student> getStudentById(Long studentId) {
        System.out.println("************* Inside getStudentsById Method **********");

        List<Student> studentById = studentRepository.findStudentById(studentId);
            if (studentById.isEmpty()){
                System.out.println("Could not find student by the given Id");
            }
            System.out.println("Found following Details for Student by Id " + studentId + " : " + studentById);
            System.out.println("************* Exiting getStudentsById Method **********");
            return studentById;

    }
}
