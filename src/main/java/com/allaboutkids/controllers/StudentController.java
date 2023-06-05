package com.allaboutkids.controllers;

import com.allaboutkids.entities.Student;
import com.allaboutkids.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("")
    public ResponseEntity<HttpStatus> createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @GetMapping("")
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable Long id) {
        return studentService.deleteStudent(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student studentDetails){
        return studentService.updateStudent(id, studentDetails);
    }

    @GetMapping("/search/{lastName}")
    public List<Student> getStudentsByLastName(@PathVariable String lastName){
        return studentService.getStudentsByLastName(lastName);
    }
}
