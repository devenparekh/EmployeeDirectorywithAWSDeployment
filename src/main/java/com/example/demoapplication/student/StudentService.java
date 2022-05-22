package com.example.demoapplication.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
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

        if(entry.isPresent()){
         throw new IllegalStateException("Email Already present");
        }
        else if (entryByName.isPresent()){
            throw new IllegalStateException("Name Already present");
        }
        else{
            studentRepository.save(student);
        }
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void deleteEntry(Long studentId) {
    boolean exists = studentRepository.existsById(studentId);
    if (!exists){
        throw new IllegalStateException("Id not present.");
    }
    studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateEntry(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(()-> new IllegalStateException("Student not found."));
        if (name != null && name.length()>0 && student.getName() != name){
            student.setName(name);
        }
        if (email != null && email.length()>0 && student.getEmail() != email){
            Optional<Student> optionalStudent = studentRepository.findStudentByEmail(email);
            if (optionalStudent.isPresent()){
                throw new IllegalStateException("Email already taken.");
            }
            student.setEmail(email);
        }
    }
}
