package com.example.springbootmongodb.controller;

import com.example.springbootmongodb.model.Student;
import com.example.springbootmongodb.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    
    @Autowired
    private StudentService studentService;
    
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        System.out.println("Getting all students");
        List<Student> students = studentService.getAllStudents();
        System.out.println("Found " + students.size() + " students");
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        System.out.println("Creating student: " + student.toString());

        Student savedStudent = studentService.createStudent(student);
        System.out.println("Created student: " + savedStudent.toString());
        
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }
} 