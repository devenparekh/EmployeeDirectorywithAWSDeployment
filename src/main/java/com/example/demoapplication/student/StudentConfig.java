package com.example.demoapplication.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student deven = new Student(
                    1L,
                    "deven",
                    "devenparekh7@gmail.com",
                    LocalDate.of(1996,10,30)
            );
            repository.save(deven);
        };
    }
}
