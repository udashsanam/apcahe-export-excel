package com.learn.fileexport.controller;


import com.learn.fileexport.model.StudentEntity;
import com.learn.fileexport.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {

    private StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @PostMapping()
    public String saveStudent(){

        for (int i = 0; i < 100; i++) {
            studentRepository.save(new StudentEntity("student " + i, "address " + i ));

        }

        return "successfully saved";
    }
}
