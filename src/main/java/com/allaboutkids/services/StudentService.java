package com.allaboutkids.services;

import com.allaboutkids.entities.Student;
import com.allaboutkids.exceptions.ResourceNotFoundException;
import com.allaboutkids.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public ResponseEntity<HttpStatus> createStudent(Student student) {
        try {
            studentRepository.save(student);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

        }
    }

    public ResponseEntity<Student> getStudentById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No student exists with id: " + id));
        return ResponseEntity.ok(student);
    }

    public ResponseEntity<HttpStatus> deleteStudent(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No student exists with id: " + id));
        studentRepository.delete(student);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public List<Student> getAllStudents() {
        List<Student> allStudents = studentRepository.findAll();
        List<Student> sortedStudents = allStudents.stream()
                .sorted(Comparator.comparing(Student::getLastName))
                .collect(Collectors.toList());
        return sortedStudents;
    }

    public ResponseEntity<Student> updateStudent(Long id, Student studentDetails) {
        Student updateStudent = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No student exists with id: " + id));
        updateStudent.setFirstName(studentDetails.getFirstName());
        updateStudent.setLastName(studentDetails.getLastName());
        updateStudent.setDateOfBirth(studentDetails.getDateOfBirth());
        updateStudent.setTeacher(studentDetails.getTeacher());
        updateStudent.setCourse(studentDetails.getCourse());
        updateStudent.setDayOfClass(studentDetails.getDayOfClass());
        updateStudent.setTimeOfClass(studentDetails.getTimeOfClass());
        updateStudent.setParent(studentDetails.getParent());


        studentRepository.save(updateStudent);
        return ResponseEntity.ok(updateStudent);
    }

    public List<Student> getStudentsByLastName(String lastName) {
        List<Student> students = studentRepository.findByQuery(lastName);

        List<Student> studentsFound = students.stream()
                .map(entry -> new Student(entry.getId(), entry.getFirstName(),entry.getLastName(),entry.getDateOfBirth(),entry.getTeacher(),entry.getCourse(),entry.getDayOfClass(),entry.getTimeOfClass(),entry.getParent()))
                .collect(Collectors.toList());

        return studentsFound;
    }
}
